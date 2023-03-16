package com.example.champomatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Matching {

    public double calculateWeightedScore(Single Single1, Single Single2) {
        double score = 0.0;

        // Calculate the score for each criterion based on user's preferences
        double genderScore = 0.0;
        if (Single1.gender == Single2.gender) {
            genderScore = 1.0;
        }

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
        }

        double hobbiesScore = 0.0;
        for (Hobbies hobby : Single1.hobbies) {
            if (Single2.hobbies.contains(hobby)) {
                hobbiesScore += 1.0 / Single1.hobbies.size();
            }
        }

        double distanceScore = 0.0;
        if (Single2.distance <= Single1.distance) {
            distanceScore = 1.0;
        }

        // Add up the weighted scores for each criterion based on user's importance weighting
        score = genderScore * 10 + ageScore * 9 + hobbiesScore * 4 + distanceScore * 7;

        return score;
    }

    public void findMatch(Single user, ArrayList<Single> candidates) {
        // Initialize the best match and the maximum score
        HashMap<Integer, Single> evaluatedCandidates = new HashMap<>();
        double maxScore = 0.0;
        Single bestMatch = null;

        // Loop through all the candidates and calculate their scores
        for (Single candidate : candidates) {
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
        if (bestMatch != null && maxScore > 25) {
            user.candidates.add(bestMatch);
        }
    }

}
