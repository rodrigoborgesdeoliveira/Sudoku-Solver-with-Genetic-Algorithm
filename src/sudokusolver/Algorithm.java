/*
 * This class implements all the genetic functions.
 */
package sudokusolver;

import java.util.Random;

/**
 *
 * @author Rodrigo
 */
public class Algorithm {
    
    private static float crossoverRate, mutationRate;
    
    /* Creates a new generation by selecting parents through the tournament 
    selection and verifying if it'll occur the crossover and mutation of the chromosomes. */
    public static Population newGeneration(Population population, boolean elitism){
        Random r = new Random();
        
        // Creates a new population, with the same size as the last one, 
        // that contains empty chromosomes.
        Population newPopulation = new Population(population.getPopulationSize());
        
        if (elitism) {
            // Keep the best chromosome from the previous generation.
            newPopulation.setChromosome(population.getBestChromosome());
        }
        
        // Inserts new chromosomes in the population, until it gets to the max size.
        while(newPopulation.getChromosomesQuantity() < newPopulation.getPopulationSize()){
            Chromosome[] parents = new Chromosome[2];
            // Selects the parents.
            parents = returnChromosomes(tournamentSelection(population));
            
            Chromosome[] children = new Chromosome[2];
            
            /* Verify if the parents will be kept in the new generation or if 
            children will be generated through crossover. */
            if (r.nextFloat() <= crossoverRate) {
                children = returnChromosomes(crossover(parents[0], parents[2]));
            } else {
                children[0] = new Chromosome(parents[0].getGenes(), true);
                children[1] = new Chromosome(parents[1].getGenes(), true);
            }
            
            // Adds the children to the new generation.
            newPopulation.setChromosome(children[0]);
            newPopulation.setChromosome(children[1]);
        }
        
        newPopulation.sortPopulation();
        
        return newPopulation;
    }
    
    // Does the crossover between two parents and generates two children.
    public static Chromosome[] crossover(Chromosome chromosome1, 
            Chromosome chromosome2){
        Random r = new Random();
        
        int lineCutPoint1, lineCutPoint2, columnCutPoint1, columnCutPoint2;
        lineCutPoint1 = r.nextInt(3);
        lineCutPoint2 = r.nextInt(4) + 5;
        columnCutPoint1 = r.nextInt(3);
        columnCutPoint2 = r.nextInt(4) + 5;
        
        Chromosome[] chromosomes = new Chromosome[2];
        
        // Genes of the parents.
        int[][] genesParents1 = chromosome1.getGenes();
        int[][] genesParents2 = chromosome2.getGenes();
        
        int[][] genesChildren1 =  new int[9][9];
        int[][] genesChildren2 = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                genesChildren1[i][j] = genesParents1[i][j];
                genesChildren2[i][j] = genesParents2[i][j];
            }
        }
    }
    
    /* Selects three chromosomes from the population randomly. 
    The best two chromosomes will be set as parents for the new generation. */
    public static Chromosome[] tournamentSelection(Population population){
        
    }
    
    // Sets the crossover rate.
    public static void setCrossoverRate(float crossoverRate){
        
    }
    
    // Returns crossover rate.
    public static float getCrossoverRate(){
        return crossoverRate;
    }
    
    // Sets the mutation rate.
    public static void setMutationRate(float mutationRate){
        
    }
    
    // Returns mutation rate.
    public static float getMutationRate(){
        return mutationRate;
    }
    
    /* This function  makes the copy of the given Chromosomes to a new variable.
    In Java, if we do ChromosomeA = ChromosomeB, the values in ChromosomeB won't 
    be copied to ChromosomeA. Instead, ChromosomeA will point to the same values 
    as ChromosomeB, which means that if we change the values of any of the variables, 
    we're actually changing the values of both variables. */
    public static Chromosome[] returnChromosomes(Chromosome[] basisChromosomes){
        
    }
}
