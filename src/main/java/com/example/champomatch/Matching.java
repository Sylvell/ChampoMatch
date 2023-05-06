package com.example.champomatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Matching {

    public double calculateWeightedScore(Single Single1, Single Single2){
        double score = 0.0;

        // Calculate the score for each criterion based on user's preferences
        double genderScore = 0.0;
        if (Single1.gender == Single2.gender) {
            genderScore = 1.0;
            return 0;
        }

        // Calculate the score for each criterion based on user's age
        double ageScore = 0.0;
        int ageDiff = Math.abs(Single1.getAge() - Single2.getAge());
        int ageMax   = Math.min(Single1.getMaximunAge(), Single2.getMaximunAge());
        int ageMin  = Math.max(Single1.getMinimunAge(), Single2.getMinimunAge());
        int ageRange = ageMax - ageMin;
        double tolerance = 0.1;
        if (ageDiff > ageRange + (ageRange * tolerance)) {
            ageScore = 0.0;
        }
        else {
            // Calcule la probabilité de la bonne entente en fonction de l'écart d'âge
            double prob = 1.0 - (double) ageDiff / (double) ageRange;
            ageScore = prob;
        }


        /*
        double ageScore = 0.0;
        if (Single2.age >= Single1.minimunAge && Single2.age <= Single1.maximunAge) {
            ageScore = 1.0;
        } else if (Single2.age < Single1.minimunAge) {
            double ageDiff = Single1.minimunAge - Single2.age;
            double ageRange = Single1.maximunAge - Single1.minimunAge;
            ageScore = Math.max(0, (ageRange - ageDiff) / ageRange);
        } else if (Single2.age > Single1.maximunAge) {
            double ageDiff = Single2.age - Single1.maximunAge;
            double ageRange = Single1.maximunAge - Single1.minimunAge;
            ageScore = Math.max(0, (ageRange - ageDiff) / ageRange);
        }*/

        double hobbiesScore = 0.0;
        for (Hobbies hobby : Single1.hobbies) {
            if (Single2.hobbies.contains(hobby)) {
                hobbiesScore += 1.0 / Single1.hobbies.size();
            }
        }
        

        double distanceScore = 0.0;

        // Calculate the distance between the two users using API Coordinates
        /*API api = new API();
        double distance = API.distance( api.getCityCoordinates(Single1.getLocalisation()), api.getCityCoordinates(Single2.getLocalisation()));
        if (distance <= Single1.distance*10 && distance <= Single2.distance*10) {
            distanceScore = 1.0;
        } else {
            double distanceDiff = distance - Single1.distance*10;
            double distanceRange = 1000 - Single1.distance*10;
            distanceScore = Math.max(0, (distanceRange - distanceDiff) / distanceRange);
        }
*/
        // Add up the weighted scores for each criterion based on user's importance weighting
        score = ageScore * 9 + hobbiesScore * 4 + distanceScore * 7;

        return score;
    }

    public Single findMatch(Single user, ArrayList<Single> candidates) {
        // Initialize the best match and the maximum score
        HashMap<Integer, Single> evaluatedCandidates = new HashMap<>();
        double maxScore = 0.0;
        Single bestMatch = null;

        // Loop through all the candidates and calculate their scores
        for (Single candidate : candidates) {
            // Skip the user himself
            if (candidate.id == user.id) {
                continue;
            }

            // Skip candidates that the user has already liked or unliked
            if (user.liked.contains(candidate) || user.unliked.contains(candidate)) {
                continue;
            }

            // Check if the candidate has already been evaluated
            if (evaluatedCandidates.containsKey(candidate.id)) {
                continue;
            }

            // Calculate the weighted score for the candidate
            double score = calculateWeightedScore(user, candidate);

            // Update the best match if the score is higher than the current maximum
            if (score > maxScore) {
                maxScore = score;
                bestMatch = candidate;
            }

            // Add the candidate to the evaluated candidates list
            evaluatedCandidates.put(candidate.id, candidate);
        }

        // Add the best match to the user's candidate list
        if (bestMatch != null ) {
            user.candidates.add(bestMatch);
            System.out.println(maxScore);
        }
        return bestMatch;
    }

    // main to test the class
    public static void main(String[] args) {
        Matching matching = new Matching();
        JdbcDao jdbcDao = new JdbcDao();
        ArrayList<Single> singles = JdbcDao.select_single();
        System.out.println(singles.get(4).toString());
        System.out.println(matching.findMatch(singles.get(4), singles));

    }

}
