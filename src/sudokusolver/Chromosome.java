/*
 * Each chromosome is a possible solution for the problem and it is part of the
 * population.
 */
package sudokusolver;

import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author Rodrigo
 */
public class Chromosome {
    
    private int[][] genes = new int[9][9];
    private int fitness = 0;

    // Copies the elements from a chromosome to another one.
    public Chromosome(Chromosome basisChromosome) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.genes[i][j] = basisChromosome.genes[i][j];
            }
        }
        fitness = basisChromosome.getFitness();
    }

    // Creates a random chromosome or, if genes parameter is true, 
    // creates a chromosome with the given genes. It is possible to mutate them.
    public Chromosome(int[][] board, boolean isGenes) {
        if (isGenes) {
            // Creates a chromosome with the given genes.
            Random r = new Random();
            
            this.genes = board;

            // Verify if it'll mutate or not.
            if (r.nextDouble() <= Algorithm.getMutationRate()) {
                // Mutates by changing the position of two genes.
                int randomLinePosition1, randomLinePosition2, randomColumnPosition1,
                        randomColumnPosition2;
                randomLinePosition1 = r.nextInt(9);
                randomColumnPosition1 = r.nextInt(9);
                randomLinePosition2 = r.nextInt(9);
                randomColumnPosition2 = r.nextInt(9);
                
                int geneTemp = this.genes[randomLinePosition1][randomColumnPosition1];
                this.genes[randomLinePosition1][randomColumnPosition1]
                        = this.genes[randomLinePosition2][randomColumnPosition2];
                this.genes[randomLinePosition2][randomColumnPosition2] = geneTemp;
            }
            
            if (r.nextDouble() <= Algorithm.getMutationRate()) {
               // Mutates by changing the value of a gene.
               int randomLinePosition = r.nextInt(9);
               int randomColumnPosition = r.nextInt(9);
               
               this.genes[randomLinePosition][randomColumnPosition] = r.nextInt(9) + 1;
               /* It's necessary to sum 1 to force it to randomly generate a number from
                    1 to 9. */
               
               fitnessFunction(); // Evaluates this new chromosome.
            }
        } else {
            // Creates a random chromosome.
            Random r = new Random();
            for (int i = 0; i < 9; i++) { // Lines
                for (int j = 0; j < 9; j++) { // Columns
                    if (board[i][j] == 0) {
                        // Generates a random number to the empty cell.
                        this.genes[i][j] = r.nextInt(9) + 1;
                        /* It's necessary to
                        sum 1 to force it to randomly generate a number from
                        1 to 9. */
                    } else {
                        /* The user already inserted a value to this cell, just
                        copy it to the chromosome's gene. */
                        this.genes[i][j] = board[i][j];
                    }
                }
            }
        }
    }

    // Evaluates the chromosome's quality by generating its fitness.
    public void fitnessFunction() {
        // Number of occurrencies of each number;
        int[] occurrences = new int[9];
        // Set all occurrencies to zero.
        for (int i = 0; i < occurrences.length; i++) {
            occurrences[i] = 0;
        }

        // Counts the number of occurrencies in each line.
        for (int i = 0; i < 9; i++) { // Lines
            for (int j = 0; j < 9; j++) { // Columns
                switch (genes[i][j]) {
                    case 1:
                        occurrences[0]++;
                        break;
                    case 2:
                        occurrences[1]++;
                        break;
                    case 3:
                        occurrences[2]++;
                        break;
                    case 4:
                        occurrences[3]++;
                        break;
                    case 5:
                        occurrences[4]++;
                        break;
                    case 6:
                        occurrences[5]++;
                        break;
                    case 7:
                        occurrences[6]++;
                        break;
                    case 8:
                        occurrences[7]++;
                        break;
                    case 9:
                        occurrences[8]++;
                        break;
                    default:
                    // There isn't any other possibility of values.
                }
            }

            // Evaluates the fitness and gives penalties.
            for (int j = 0; j < occurrences.length; j++) {
                /* As each number can only have one occurrence, we must subtract 1 from 
                the occurrences variable. If it only has one occurrence, it'll 
                result in 0, which doesn't represent a penalty to the fitness.
                As it can not even occur at all (appears 0 times), it's necessary
                to attribute a penalty as well, but subtracting the occurrences 
                variable by 1, would lead to the number -1, so, it's necessary to
                use the abs() function to result in the positive number 1.*/
                fitness += abs(occurrences[j] - 1);
                occurrences[j] = 0; // Reset the number of occurrences for this number,
                // as we don't need it anymore.
            }
        }

        // Counts the number of occurrencies in each column.
        for (int i = 0; i < 9; i++) { // Columns
            for (int j = 0; j < 9; j++) { // Lines
                switch (genes[j][i]) {
                    case 1:
                        occurrences[0]++;
                        break;
                    case 2:
                        occurrences[1]++;
                        break;
                    case 3:
                        occurrences[2]++;
                        break;
                    case 4:
                        occurrences[3]++;
                        break;
                    case 5:
                        occurrences[4]++;
                        break;
                    case 6:
                        occurrences[5]++;
                        break;
                    case 7:
                        occurrences[6]++;
                        break;
                    case 8:
                        occurrences[7]++;
                        break;
                    case 9:
                        occurrences[8]++;
                        break;
                    default:
                    // There isn't any other possibility of values.
                }
            }

            // Evaluates the fitness and gives penalties.
            for (int j = 0; j < occurrences.length; j++) {
                /* As each number can only have one occurrence, we must subtract 1 from 
                the occurrences variable. If it only has one occurrence, it'll 
                result in 0, which doesn't represent a penalty to the fitness.
                As it can not even occur at all (appears 0 times), it's necessary
                to attribute a penalty as well, but subtracting the occurrences 
                variable by 1, would lead to the number -1, so, it's necessary to
                use the abs() function to result in the positive number 1. */
                fitness += abs(occurrences[j] - 1);
                occurrences[j] = 0; // Reset the number of occurrences for this number,
                // as we don't need it anymore.
            }
        }

        // Counts the number of occurrencies in each square.
        int lineBeginning, lineEnding, columnBeginning, columnEnding;
        for (lineBeginning = 0; lineBeginning < 7; lineBeginning += 3) {
            lineEnding = lineBeginning + 3;
            for (columnBeginning = 0; columnBeginning < 7; columnBeginning += 3) {
                columnEnding = columnBeginning + 3;
                for (int i = lineBeginning; i < lineEnding; i++) { // Lines
                    for (int j = columnBeginning; j < columnEnding; j++) { // Columns
                        switch (genes[i][j]) {
                            case 1:
                                occurrences[0]++;
                                break;
                            case 2:
                                occurrences[1]++;
                                break;
                            case 3:
                                occurrences[2]++;
                                break;
                            case 4:
                                occurrences[3]++;
                                break;
                            case 5:
                                occurrences[4]++;
                                break;
                            case 6:
                                occurrences[5]++;
                                break;
                            case 7:
                                occurrences[6]++;
                                break;
                            case 8:
                                occurrences[7]++;
                                break;
                            case 9:
                                occurrences[8]++;
                                break;
                            default:
                            // There isn't any other possibility of values.
                        }
                    }
                }

                // Evaluates the fitness and gives penalties.
                for (int j = 0; j < occurrences.length; j++) {
                    /* As each number can only have one occurrence, we must subtract 1 from 
                        the occurrences variable. If it only has one occurrence, it'll 
                        result in 0, which doesn't represent a penalty to the fitness.
                        As it can not even occur at all (appears 0 times), it's necessary
                        to attribute a penalty as well, but subtracting the occurrences 
                        variable by 1, would lead to the number -1, so, it's necessary to
                        use the abs() function to result in the positive number 1. */
                    fitness += abs(occurrences[j] - 1);
                    occurrences[j] = 0; // Reset the number of occurrences for this number,
                    // as we don't need it anymore.
                }
            }
        }
    }
    
    public int getFitness() {
        return fitness;
    }
    
    public int[][] getGenes() {
        return genes;
    }
}
