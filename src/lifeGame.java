//
//
//My first project build in java at the beginning of university
//
//
import java.util.Random;

class Macierz {
    private int n;
    private int m;
    private int[][] Macierz;
    private int[][] NextMacierz;

    public Macierz(int n, int m) {
        this.n = n;
        this.m = m;
        this.Macierz = new int[n][m];
        this.NextMacierz = new int[n][m];
    }

    public int[][] getMacierz() {
        return Macierz;
    }

    public int[][] getNextMacierz() {return NextMacierz; }

    public void wypelnijMacierz() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Random g = new Random();
                this.Macierz[i][j] = g.nextInt(2);
            }

        }
    }

    public void WydrukujMacierz() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (this.Macierz[i][j] == 1)
                    System.out.print("■ ");
                else
                    System.out.print("  ");
                //System.out.print(this.Macierz[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void isAliveInNextGen(int x, int y){

        int neighbours = 0;
        //m - x, y - n      Macierz[n][m]
        int xmin = (x - 1 + m) % m;
        int xplus = (x + 1 + m) % m;
        int ymin = (y - 1 + n) % n;
        int yplus = (y + 1 + n) % n;
        if (this.Macierz[ymin][xmin] == 1) neighbours += 1;
        if (this.Macierz[ymin][x] == 1) neighbours += 1;
        if (this.Macierz[ymin][xplus] == 1) neighbours += 1;

        if (this.Macierz[y][xplus] == 1) neighbours += 1;
        if (this.Macierz[y][xmin] == 1) neighbours += 1;

        if (this.Macierz[yplus][xmin] == 1) neighbours += 1;
        if (this.Macierz[yplus][x] == 1) neighbours += 1;
        if (this.Macierz[yplus][xplus] == 1) neighbours += 1;

        //stan w nastepnym kroku
        if (this.Macierz[y][x] == 1){
            if(neighbours == 2 || neighbours == 3)
                this.NextMacierz[y][x] = 1;
            else
                this.NextMacierz[y][x] = 0;
        }
        else{
            if(neighbours == 3)
                this.NextMacierz[y][x] = 1;
            else
                this.NextMacierz[y][x] = 0;
        }
    }

    public void NextMatrix() {
        for (int i = 0; i < n; i++) {       //kolumna
            for (int j = 0; j < m; j++) {   //linia
                // j - x, i - y
                isAliveInNextGen(j, i);         //przechodzi po kazdej klatkie
            }
        }
    }



    public void changeMatrix() {        //zamieniamy obecną macierz na następną po wyswietleniu
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.Macierz[i][j] = this.NextMacierz[i][j];
            }
        }
    }
}

public class lifeGame {

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public static void main(String[] args){
        Macierz Macierz = new Macierz(70, 125); //y, x
        Macierz.wypelnijMacierz();

        while(true){
            cls();
            Macierz.WydrukujMacierz();
            Macierz.NextMatrix();
            Macierz.changeMatrix();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
