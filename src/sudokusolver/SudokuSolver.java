/*
 * Genetic Algorithm to solve the classic 9x9 Sudoku Game.
 */
package sudokusolver;

import static java.lang.Math.abs;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Rodrigo Borges
 */
public class SudokuSolver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Use system appearance.
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
                | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Interface().setVisible(true);
    }
    
}
