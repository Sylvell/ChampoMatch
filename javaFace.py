import os
import urllib.request
import cv2
from pyagender import PyAgender
import pymysql
from ftplib import FTP_TLS
import time
import requests

# Connect to MySQL database
db = pymysql.connect(host="montou.o2switch.net", user='hdyx5526_ChampoMatch', password='ChampoMatch', db='hdyx5526_ChampoMatch')
cursor = db.cursor()

# Connect to FTP server using TLS port 21
host = 'ftp.hdyx5526.odns.fr'
port = 21
usr = 'ChampoMatch@champomatch.hdyx5526.odns.fr'
pwd = 'ChampoMatch'
ftp = FTP_TLS()
ftp.connect(host, port)
ftp.login(usr, pwd)
# FTP server in the images directory
ftp.cwd('images')

for i in range(1000):
    # Download image from URL with delay
    response = requests.get("https://100k-faces.glitch.me/random-image-url")
    url = response.json()["url"]
    filename = url.split('/')[-1]
    # check if the file already exists in the database
    sql = "SELECT * FROM face_data WHERE image_path = '{}'".format(filename)
    cursor.execute(sql)
    result = cursor.fetchone()
    if result:
        continue
    urllib.request.urlretrieve(url, filename)
    time.sleep(1)  # Delay of 1 second between each image download

    # Detect gender and age from the image
    agender = PyAgender() # Initialize the agender object
    faces = agender.detect_genders_ages(cv2.imread(filename))

    # Insert gender, age, image ID, and path data into the database
    if (len(faces) > 0):
        gender = faces[0]['gender']
        # transform the gender float value into a string
        if gender > 0.5:
            gender = "Female"
        else:
            gender = "Male"
        age = int(faces[0]['age'])
        sql = "INSERT INTO face_data (gender, age, image_path) VALUES ( %s, %s, %s)"
        values = (gender, age, filename)
        cursor.execute(sql, values)
        db.commit()

        with open(filename, 'rb') as f:
            ftp.storbinary('STOR {}'.format(filename), f)

    # Delete the image from the local directory
    os.remove(filename)

ftp.quit()
db.close()
