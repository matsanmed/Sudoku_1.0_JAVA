package sudoku_ed_mi;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Sudo {
    int mGabarito[][] = new int[9][9];	// matriz que contem o gabarito
    int  mResp [][]=new int[9][9];// matriz que contem a resposta do usuario
    Random nRand = new Random();
    int liq = 0;	// linha inicial do quadrado
    int ciq = 0;	// coluna inicial do quadrado
    int lfq = 0;	// linha final do quadrado
    int cfq = 0;	// coluna final do quadrado
    int linha = 0;	// linha de trabalho -> insercao de numeros
    int coluna = 0;	// coluna de trabalho -> insercao de numeros
    void acharLinhaColunaVazia() {
        linha = Math.abs(nRand.nextInt() % 3);
        coluna = Math.abs(nRand.nextInt() % 3);
        while (mGabarito[liq + linha][ciq + coluna] != 0) {
            linha = Math.abs(nRand.nextInt() % 3);
            coluna = Math.abs(nRand.nextInt() % 3);
        }
    }

    boolean verificarRepeticaoLinha(int linha) {
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if ((mGabarito[linha][i] != 0)
                        && (mGabarito[linha][i] == mGabarito[linha][j])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean verificarRepeticaoColuna(int coluna) {
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if ((mGabarito[i][coluna] != 0)
                        && (mGabarito[i][coluna] == mGabarito[j][coluna])) {
                    return true;
                }
            }
        }
        return false;
    }

    void acharLimitesQuadrado(int q) {
            if (q == 1) {
            liq = 0;
            ciq = 0;
            lfq = 2;
            cfq = 2;
        }
        if (q == 2) {
            liq = 0;
            ciq = 3;
            lfq = 2;
            cfq = 5;
        }
        if (q == 3) {
            liq = 0;
            ciq = 6;
            lfq = 2;
            cfq = 8;
        }
        if (q == 4) {
            liq = 3;
            ciq = 0;
            lfq = 5;
            cfq = 2;
        }
        if (q == 5) {
            liq = 3;
            ciq = 3;
            lfq = 5;
            cfq = 5;
        }
        if (q == 6) {
            liq = 3;
            ciq = 6;
            lfq = 5;
            cfq = 8;
        }
        if (q == 7) {
            liq = 6;
            ciq = 0;
            lfq = 8;
            cfq = 2;
        }
        if (q == 8) {
            liq = 6;
            ciq = 3;
            lfq = 8;
            cfq = 5;
        }
        if (q == 9) {
            liq = 6;
            ciq = 6;
            lfq = 8;
            cfq = 8;
        }   
    }

    void zerarQuadrado() {
       
        for (int l = liq; l <= lfq; l++) {
            for (int c = ciq; c <= cfq; c++) {
                mGabarito[l][c] = 0;
            }
        }
}
     
    void zerarDados() {
        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                mGabarito[l][c] = 0;
            }
        }
        liq = 0;	// linha inicial do quadrado
        ciq = 0;	// coluna inicial do quadrado
        lfq = 0;	// linha final do quadrado
        cfq = 0;	// coluna final do quadrado
        linha = 0;	// linha de trabalho -> insercao de numeros
        coluna = 0;	// coluna de trabalho -> insercao de numeros
    }
 
    void alocarNumeros() {
        int quadrado;
        int tentativas;
        int voltar = 0;
        int ultimoQuadradoRepetido = 0;
        boolean vrl, vrc;
        zerarDados();
        for (quadrado = 1; quadrado < 10; quadrado++) {
            acharLimitesQuadrado(quadrado);
            for (int n = 1; n < 10; n++) {
                acharLinhaColunaVazia();
                mGabarito[liq + linha][ciq + coluna] = n;
                vrl = verificarRepeticaoLinha(liq + linha);
                vrc = verificarRepeticaoColuna(ciq + coluna);
                tentativas = 0;
                while (vrl || vrc) {
                    if (tentativas == 100) {
                        if (quadrado == ultimoQuadradoRepetido) {
                            if (voltar < quadrado) {
                                voltar++;
                            }
                            break;
                        } else {
                            voltar = 1;
                            ultimoQuadradoRepetido = quadrado;
                            break;
                        }
                    }
                    tentativas++;
                    
                    mGabarito[liq + linha][ciq + coluna] = 0;
                    acharLinhaColunaVazia();
                    mGabarito[liq + linha][ciq + coluna] = n;
                    vrl = verificarRepeticaoLinha(liq + linha);
                    vrc = verificarRepeticaoColuna(ciq + coluna);
                }
                if (tentativas == 100) {
                    for (int q = quadrado; q > quadrado - voltar; q--) {
                        acharLimitesQuadrado(q);
                        zerarQuadrado();
                    }
                    quadrado -= voltar;
                    break;
                }
            }
        }
    }
    
    void imprimisud(){
        
        JOptionPane.showConfirmDialog
                (null, " " + mGabarito[0][0] + " " + mGabarito[0][1] + " " + mGabarito[0][2] + "     " + mGabarito[0][3] + " " + mGabarito[0][4] + " " + mGabarito[0][5] + "     " + mGabarito[0][6] + " " + mGabarito[0][7] + " " + mGabarito[0][8]+
                     "\n " + mGabarito[1][0] + " " + mGabarito[1][1] + " " + mGabarito[1][2] + "     " + mGabarito[1][3] + " " + mGabarito[1][4] + " " + mGabarito[1][5] + "     " + mGabarito[1][6] + " " + mGabarito[1][7] + " " + mGabarito[1][8]+
                     "\n " + mGabarito[2][0] + " " + mGabarito[2][1] + " " + mGabarito[2][2] + "     " + mGabarito[2][3] + " " + mGabarito[2][4] + " " + mGabarito[2][5] + "     " + mGabarito[2][6] + " " + mGabarito[2][7] + " " + mGabarito[2][8]+
                   "\n\n " + mGabarito[3][0] + " " + mGabarito[3][1] + " " + mGabarito[3][2] + "     " + mGabarito[3][3] + " " + mGabarito[3][4] + " " + mGabarito[3][5] + "     " + mGabarito[3][6] + " " + mGabarito[3][7] + " " + mGabarito[3][8]+
                     "\n " + mGabarito[4][0] + " " + mGabarito[4][1] + " " + mGabarito[4][2] + "     " + mGabarito[4][3] + " " + mGabarito[4][4] + " " + mGabarito[4][5] + "     " + mGabarito[4][6] + " " + mGabarito[4][7] + " " + mGabarito[4][8]+
                     "\n " + mGabarito[5][0] + " " + mGabarito[5][1] + " " + mGabarito[5][2] + "     " + mGabarito[5][3] + " " + mGabarito[5][4] + " " + mGabarito[5][5] + "     " + mGabarito[5][6] + " " + mGabarito[5][7] + " " + mGabarito[5][8]+
                   "\n\n " + mGabarito[6][0] + " " + mGabarito[6][1] + " " + mGabarito[6][2] + "     " + mGabarito[6][3] + " " + mGabarito[6][4] + " " + mGabarito[6][5] + "     " + mGabarito[6][6] + " " + mGabarito[6][7] + " " + mGabarito[6][8]+
                     "\n " + mGabarito[7][0] + " " + mGabarito[7][1] + " " + mGabarito[7][2] + "     " + mGabarito[7][3] + " " + mGabarito[7][4] + " " + mGabarito[7][5] + "     " + mGabarito[7][6] + " " + mGabarito[7][7] + " " + mGabarito[7][8]+
                     "\n " + mGabarito[8][0] + " " + mGabarito[8][1] + " " + mGabarito[8][2] + "     " + mGabarito[8][3] + " " + mGabarito[8][4] + " " + mGabarito[8][5] + "     " + mGabarito[8][6] + " " + mGabarito[8][7] + " " + mGabarito[8][8], "a batalha acabou", 0);
    }
    
        void esconderNumeros(int quantidade) {
        int l, c;

        for (l = 0; l < 9; l++) {
            for (c = 0; c < 9; c++) {
                mResp[l][c] = mGabarito[l][c];
            }
        }

        for (int q = 0; q < quantidade; q++) {
            l = Math.abs(nRand.nextInt() % 9);
            c = Math.abs(nRand.nextInt() % 9);
            while (mResp[l][c] == 0) {
                l = Math.abs(nRand.nextInt() % 9);
                c = Math.abs(nRand.nextInt() % 9);
            }
            mResp[l][c] = 0;
        }
    }

    int [][] jogar(int quantidadeBrancos) {
        zerarDados();
        alocarNumeros();
        esconderNumeros(quantidadeBrancos);

        return mResp;
    }

   
    


}