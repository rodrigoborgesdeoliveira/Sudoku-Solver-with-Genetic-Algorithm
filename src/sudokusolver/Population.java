/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

/**
 *
 * @author Rodrigo
 */
public class Population {
    
    private int populationSize;
    private Chromosome[] chromosomes;
    
    // Copies the elements from a population to another one.    
    public Population(Population basisPopulation){
        
    }
    
    // Creates a population  that contains random chromosomes.
    public Population(int populationSize, int[][] board){
        
    }
    
    // Creates a population with empty chromosomes to be filled later.
    public Population(int populationSize){
        
    }
    
    // Inserts a chromosome in a given position of the population.
    public void setChromosome(Chromosome chromosome, int position){
        
    }   
    
    // Inserts a chromosome in the first available position (where Chromosome = null).
    public void setChromosome(Chromosome chromosome){
        
    }
    
    // Returns the chromosome of the given position.
    public Chromosome getChromosome(int position){
        return chromosomes[position];
    }
    
    // Sorts the population from the best chromosome (lowest fitness) to the worst (biggest fitness).
    public void sortPopulation(){
        
    }
    
    // Returns the quantity of non-null chromosomes.
    public int getChromosomesQuantity(){
        int qtt = 0;
        for(Chromosome chromosome : chromosomes){
            if (chromosome != null) {
                qtt++;
            }
        }
        
        return qtt;
    }
    
    // Returns the chromosome with the best (lowest) fitness.
    public Chromosome getBestChromosome(){
        sortPopulation();
        return chromosomes[0];
    }
    
    // Returns the size of the population.
    public int getPopulationSize(){
        return populationSize;
    }
}
