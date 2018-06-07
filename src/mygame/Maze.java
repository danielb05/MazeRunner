package mygame;

/**
 *
 * @authors Emanuel(150329) e Daniel(132118) 
 *
 */
public class Maze {
    
    /**
    *
    * Composição do Labirinto:
    * 
    *   1 - Parede
    *   2 - Prêmio 
    *   3 - Jogador
    *
    */
    private int maze[][];
    
    {
        maze = new int[40][40];

    }
    
    public void generate(int op)
    {
        switch (op)
        {
            case 0:
                maze = new int[][]{
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,3,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,0,1,1,0,0,1,0,1},
                   {1,0,1,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,1,0,0,1,0,0,1,1,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,0,1,0,1,0,0,1,1,1,0,1,1},
                   {1,0,1,0,1,1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,0,1,1,1,0,1},
                   {1,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,0,1,1,1},
                   {1,0,1,1,0,1,0,1,1,0,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1},
                   {1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,0,1,0,1,0,0,0,1},
                   {1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,0,0,0,1},
                   {1,0,1,0,0,0,1,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,1,1,0,0,0,0,0,1,0,0,1,0,1},
                   {1,0,1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,1},
                   {1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,1},
                   {1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1},
                   {1,0,1,0,0,0,1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,0,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,1},
                   {1,0,0,0,1,0,0,0,1,0,1,1,0,0,1,1,1,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,1},
                   {1,0,0,0,1,1,0,1,1,1,0,0,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,1,0,1,1,1,1,1,1},
                   {1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,1,0,1,0,1,1,0,0,0,0,1,1},
                   {1,0,1,1,1,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,2,0,0,1,1,0,0,0,1,0,0,1,1,0,0,0,0,1,1},
                   {1,0,0,0,1,1,0,0,1,1,1,0,0,1,1,0,1,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,0,1,1,0,1},
                   {1,0,0,0,1,1,1,1,1,1,0,1,0,0,1,0,1,1,1,0,0,0,0,0,1,1,0,0,1,0,0,1,1,1,0,0,1,1,0,1},
                   {1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,0,0,1,0,1,0,0,1,1,1,1,1,1,0,0,1},
                   {1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1,0,1,1,1},
                   {1,0,1,1,1,1,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1,0,1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,1},
                   {1,0,1,1,0,1,0,0,0,0,1,1,0,0,1,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,0,0,1,1,1},
                   {1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,1,0,1,1},
                   {1,1,0,1,1,1,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,1,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,1,0,0,0,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,1,0,1},
                   {1,1,0,0,1,0,1,0,1,1,0,1,0,0,1,1,0,0,1,1,1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,0,1,0,0,1},
                   {1,0,1,0,1,0,0,0,0,0,0,1,1,0,1,1,1,1,1,0,0,0,0,0,1,0,0,0,1,1,0,0,1,1,0,1,1,0,0,1},
                   {1,1,1,0,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,1,1,1,1,0,1,0,0,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,0,1,1,0,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,1},
                   {1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,1,1,0,0,1},
                   {1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,1,1,0,0,0,1,0,0,1,0,1},
                   {1,1,1,1,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,0,1},
                   {1,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,1,0,1,1,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
                
            case 1:
                maze = new int[][]{
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,3,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,0,1,1,0,0,1,0,1},
                   {1,0,1,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,1,0,0,1,0,0,1,1,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,0,1,0,1,0,0,1,1,1,0,1,1},
                   {1,0,1,0,1,1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,1,1,0,1,0,1,1,0,0,1,1,0,1,0,1,0,1,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,1,0,1,1,1,0,1},
                   {1,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,0,1,1,1},
                   {1,0,1,1,0,1,0,1,1,0,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,1,1,0,1,0,1,0,1,0,1,1,1,1},
                   {1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,0,1,0,1,0,0,0,1},
                   {1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,0,0,0,1},
                   {1,0,1,0,0,0,1,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,1,1,0,1,1,0,0,0,0,0,1,0,0,1,0,1},
                   {1,0,1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,1},
                   {1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,1},
                   {1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,0,0,0,0,1,0,0,2,0,0,0,0,1,0,1,0,1,0,0,1,0,1},
                   {1,0,1,0,0,0,1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,0,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,1},
                   {1,0,0,0,1,0,0,0,1,0,1,1,0,0,1,1,1,1,0,1,0,0,1,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,1},
                   {1,0,0,0,1,1,0,1,1,1,0,0,1,0,0,0,1,1,1,1,0,0,0,1,0,0,0,1,0,0,1,1,1,0,1,1,1,1,1,1},
                   {1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,1,0,1,0,1,1,0,0,0,0,1,1},
                   {1,0,1,1,1,1,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,0,1,1,1,0,0,0,1,0,0,1,1,0,0,0,0,1,1},
                   {1,0,0,0,1,1,0,0,1,1,1,0,0,1,1,0,1,0,1,0,0,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,1,0,1},
                   {1,0,0,0,1,1,1,1,1,1,0,1,0,0,1,0,1,1,1,1,0,0,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1,1,0,1},
                   {1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,1,1,1,1,1,1,0,0,1},
                   {1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1,0,1,1,1},
                   {1,0,1,1,1,1,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1,0,1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,1},
                   {1,0,1,1,0,1,0,0,0,0,1,1,0,0,1,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,0,0,1,1,1},
                   {1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,1,0,1,1},
                   {1,1,0,1,1,1,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,1,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,1,0,0,0,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,1,0,1},
                   {1,1,0,0,1,0,1,0,1,1,0,1,0,0,1,1,0,0,1,1,1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,0,1,0,0,1},
                   {1,0,1,0,1,0,0,0,0,0,0,1,1,0,1,1,1,1,1,0,0,0,0,0,1,0,0,0,1,1,0,0,1,1,0,1,1,0,0,1},
                   {1,1,1,0,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,1,1,1,1,0,1,0,0,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,0,1,1,0,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,1},
                   {1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,1,1,0,0,1},
                   {1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,1,1,0,0,0,1,0,0,1,0,1},
                   {1,1,1,1,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,0,1},
                   {1,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,1,0,1,1,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
                
            case 2:
                maze = new int[][]{
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                   {1,0,3,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0,0,0,0,0,0,1,0,1,1,0,0,1,0,1},
                   {1,0,1,0,0,0,0,1,0,0,1,1,1,1,0,1,1,0,0,1,1,0,0,0,0,0,0,1,1,1,0,0,1,0,0,1,1,0,0,1},
                   {1,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,1,0,0,0,0,1,1,0,0,0,1},
                   {1,0,1,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1,0,1,0,1,1,0,0,1,0,0,1,0,0,0,1,0,1},
                   {1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,1,1,1,0,1},
                   {1,0,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,0,1,0,1},
                   {1,0,1,1,0,1,0,1,1,0,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,1,1,0,1,0,1,0,1,0,1,1,0,1},
                   {1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,0,1,1,1,1,0,0,1,1,1,1,0,1,0,1,1,0,0,1,0,1,0,0,0,1},
                   {1,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,0,0,0,0,0,1,0,1,0,1,1,0,1,1,1,1,0,1,0,0,0,0,0,1},
                   {1,0,1,0,0,0,1,0,1,0,0,0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,1,1,0,0,0,0,0,1,0,0,1,0,1},
                   {1,0,1,1,1,0,1,1,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,1},
                   {1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,1},
                   {1,0,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,0,0,1,0,1,0,0,0,0,1,0,0,1,0,1,0,1,0,0,1,0,1},
                   {1,0,1,0,0,0,1,0,1,1,0,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
                   {1,0,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,0,1,0,1,1,0,1,0,0,0,1,0,0,1,1,0,1,1,1,1,1,0,1},
                   {1,0,0,0,1,0,0,0,1,0,1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,1},
                   {1,0,0,0,1,1,0,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,1,1,1,1,1,1},
                   {1,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,1,1,0,0,2,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,1,1},
                   {1,0,1,1,1,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,0,0,0,0,1,1,0,0,0,1,0,0,1,1,0,0,0,0,1,1},
                   {1,0,0,0,1,1,0,0,1,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,0,0,1,1,0,1},
                   {1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,0,1,1,0,0,0,1,1,1,0,0,1,0,0,1,1,1,0,0,1,1,0,1},
                   {1,1,1,1,0,1,1,0,1,1,1,1,1,1,0,1,1,0,1,1,0,1,0,0,0,0,1,0,1,0,0,1,1,1,0,1,1,0,0,1},
                   {1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1,0,1,1,1},
                   {1,0,1,1,1,1,0,1,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1,0,1,0,0,1,1,0,0,0,0,1,0,0,1,0,0,1},
                   {1,0,1,1,0,1,0,0,0,0,1,1,0,0,1,1,1,0,0,0,0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,1,1,1,0,1,0,1,1,0,1,1,0,1,1,0,0,1,0,0,0,0,0,1,0,0,1,1,1},
                   {1,1,0,0,0,0,1,1,1,1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,1,1,1,1,1,0,1,1},
                   {1,1,0,1,1,1,0,0,0,0,0,1,0,0,1,0,1,1,0,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,1,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,0,0,1,1,1,1,0,1,1,1,0,0,1,0,1,1,0,0,0,1,1,0,1},
                   {1,0,0,0,0,1,1,0,1,0,0,1,0,0,0,1,1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,1,0,1},
                   {1,1,0,0,1,0,1,0,1,1,0,1,0,0,1,1,0,0,1,1,1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,0,1,0,0,1},
                   {1,0,1,0,1,0,0,0,0,0,0,1,1,0,1,1,1,1,1,0,0,0,0,0,1,0,0,0,1,1,0,0,1,1,0,0,1,0,0,1},
                   {1,1,1,0,0,0,1,0,1,1,1,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,1,1,1,1,0,1,0,0,1,1,1},
                   {1,0,0,1,0,0,1,0,0,1,1,1,0,1,1,0,1,0,0,0,0,0,1,1,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,1},
                   {1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,1,0,1,0,0,1,0,0,0,0,1,1,1,0,0,1},
                   {1,0,1,0,1,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1,0,0,1,1,1,0,1,0,0,1,1,0,0,0,1,0,0,1,0,1},
                   {1,1,1,1,0,1,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,0,0,1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,0,1},
                   {1,0,1,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,1,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,1,0,1,1,0,1},
                   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
                break;
        }

                        
    }

    public int[][] getMaze() {
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
    
    
    
}
