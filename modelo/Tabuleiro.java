package modelo;
public class Tabuleiro{

  private Peca[][] tab = 
  {
    {new Torre(), new Cavalo(), new Bispo(), new Rainha(), new Rei(), new Bispo(), new Cavalo(), new Torre()},
    {new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao()},
    {null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null},
    {null, null, null, null, null, null, null, null},
    {new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao(), new Peao()},
    {new Torre(), new Cavalo(), new Bispo(), new Rainha(), new Rei(), new Bispo(), new Cavalo(), new Torre()}
    
    };

  public void Ver(){

    for (int i = 0; i < tab.length; i++) {
      
      for (int j = 0; j < tab.length; j++) {
        
        System.out.print(tab[i][j] + " ");

      }

      System.out.println();

    }

  }  
  
} 