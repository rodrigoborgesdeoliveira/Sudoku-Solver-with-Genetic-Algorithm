/*
 * Population contains many possible solutions. The algorithm will try to solve 
 * the problem by getting these possible solutions from the population and combining 
 * themselves.
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
        this.populationSize = basisPopulation.populationSize;
        this.chromosomes = new Chromosome[populationSize];
        
        for (int i = 0; i < this.populationSize; i++) {
            this.chromosomes[i] = new Chromosome(basisPopulation.chromosomes[i]);
        }
    }
    
    // Creates a population  that contains random chromosomes.
    public Population(int populationSize, int[][] board){
        this.populationSize = populationSize;
        chromosomes = new Chromosome[populationSize];
        
        // Creates the random chromosomes.
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = new Chromosome(board, false);
        }
        
        sortPopulation();
    }
    
    // Creates a population with empty chromosomes to be filled later.
    public Population(int populationSize){
        this.populationSize = populationSize;
        chromosomes = new Chromosome[populationSize];
        
        // Creates empty chromosomes.
        for (int i = 0; i < chromosomes.length; i++) {
            chromosomes[i] = null;
        }
    }
    
    // Inserts a chromosome in a given position of the population.
    public void setChromosome(Chromosome chromosome, int position){
        chromosomes[position] = new Chromosome(chromosome);
    }   
    
    // Inserts a chromosome in the first available position (where Chromosome = null).
    public void setChromosome(Chromosome chromosome){
        for (int i = 0; i < chromosomes.length; i++) {
            if (chromosomes[i] == null) {
                chromosomes[i] = new Chromosome(chromosome);
                return;
            }
        }
    }
    
    // Returns the chromosome of the given position.
    public Chromosome getChromosome(int position){
        return chromosomes[position];
    }
    
    // Sorts the population from the best chromosome (lowest fitness) to the worst (biggest fitness).
    public void sortPopulation(){
        boolean changed;
        do {            
            changed = false;
            for (int i = 1; i < chromosomes.length; i++) {
                if (chromosomes[i].getFitness() < chromosomes[i - 1].getFitness()) {
                    // The current chromosome is better than the previous one.
                    Chromosome temp = chromosomes[i];
                    chromosomes[i] = chromosomes[i - 1];
                    chromosomes[i - 1] = temp;
                    
                    changed = true;
                }
            }
        } while (changed);
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
