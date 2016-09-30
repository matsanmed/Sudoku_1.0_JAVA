package sudoku_ed_mi;

import java.util.Random;
import javax.swing.JOptionPane;
public class Sudo {
    int gabaMatriz[][] = new int[9][9];	// matriz que contem o gabarito
    int respMatriz [][]=new int[9][9];// matriz que contem a resposta do usuario
    Random varRand = new Random();
    private int plq = 0;	// primeira linha do quadrado
    private int pcq = 0;	// primeira coluna do quadrado
    private int ulq = 0;	// ultima linha do quadrado
    private int ucq = 0;	// ultima coluna do quadrado
    private int linha = 0;	// linha de trabalho -> insercao de numeros
    private int coluna = 0;	// coluna de trabalho -> insercao de numeros
    
    void pesqEspVazio() {       // pesquisa espaços vazios
        setLinha(Math.abs(varRand.nextInt() % 3));// numero aleatorio de 0 a 2 para as linhas do quadrado 3x3
        setColuna(Math.abs(varRand.nextInt() % 3));//numero aleatorio de 0 a 2 para as coluna do quadrado 3x3
        //verifia se ja existe um numero na linha e coluna sorteada e procura espaço vazio
        while (gabaMatriz[getPlq() + getLinha()][getPcq() + getColuna()] != 0) {
            setLinha(Math.abs(varRand.nextInt() % 3));
            setColuna(Math.abs(varRand.nextInt() % 3));
        }
    }

    boolean pesqLinhaRep(int linha) {
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if ((gabaMatriz[linha][i] != 0)
                        && (gabaMatriz[linha][i] == gabaMatriz[linha][j])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean pesqColunaRep(int coluna) {
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if ((gabaMatriz[i][coluna] != 0)
                        && (gabaMatriz[i][coluna] == gabaMatriz[j][coluna])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean pesqQuadRep(int quadrado) {     // pesquisa os quadrados repetidos
        pesqLimQuad(quadrado);
        int nQuadrado[] = new int[9];
        for (int i = 0; i < 9; i++) {
            nQuadrado[i] = 0;
        }

        nQuadrado[0] = respMatriz[getPlq()][getPcq()];
        nQuadrado[1] = respMatriz[getPlq()][getPcq() + 1];
        nQuadrado[2] = respMatriz[getPlq()][getPcq() + 2];
        nQuadrado[3] = respMatriz[getPlq() + 1][getPcq()];
        nQuadrado[4] = respMatriz[getPlq() + 1][getPcq() + 1];
        nQuadrado[5] = respMatriz[getPlq() + 1][getPcq() + 2];
        nQuadrado[6] = respMatriz[getPlq() + 2][getPcq()];
        nQuadrado[7] = respMatriz[getPlq() + 2][getPcq() + 1];
        nQuadrado[8] = respMatriz[getPlq() + 2][getPcq() + 2];

        for (int i = 0; i < 9; i++) {
            if (nQuadrado[i] == 0) {
                return true;
            }
        }

        return false;
    }

    void pesqLimQuad(int q) {       // pesquisa os limites do quadrado
            if (q == 1) {
            setPlq(0);
            setPcq(0);
            setUlq(2);
            setUcq(2);
        }
        if (q == 2) {
            setPlq(0);
            setPcq(3);
            setUlq(2);
            setUcq(5);
        }
        if (q == 3) {
            setPlq(0);
            setPcq(6);
            setUlq(2);
            setUcq(8);
        }
        if (q == 4) {
            setPlq(3);
            setPcq(0);
            setUlq(5);
            setUcq(2);
        }
        if (q == 5) {
            setPlq(3);
            setPcq(3);
            setUlq(5);
            setUcq(5);
        }
        if (q == 6) {
            setPlq(3);
            setPcq(6);
            setUlq(5);
            setUcq(8);
        }
        if (q == 7) {
            setPlq(6);
            setPcq(0);
            setUlq(8);
            setUcq(2);
        }
        if (q == 8) {
            setPlq(6);
            setPcq(3);
            setUlq(8);
            setUcq(5);
        }
        if (q == 9) {
            setPlq(6);
            setPcq(6);
            setUlq(8);
            setUcq(8);
        }   
    }

    void limpaQuad() {
       
        for (int l = getPlq(); l <= getUlq(); l++) {
            for (int c = getPcq(); c <= getUcq(); c++) {
                gabaMatriz[l][c] = 0;
            }
        }
}
     
    void limpaDados() {
        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                gabaMatriz[l][c] = 0;
            }
        }
        setPlq(0);	// linha inicial do quadrado
        setPcq(0);	// coluna inicial do quadrado
        setUlq(0);	// linha final do quadrado
        setUcq(0);	// coluna final do quadrado
        setLinha(0);	// linha de trabalho -> insercao de numeros
        setColuna(0);	// coluna de trabalho -> insercao de numeros
    }
 
    void salvaNumeros() {
        int quadrado;
        int tentativas;
        int voltar = 0;
        int ultQuadRep = 0;
        boolean vrl, vrc;
        limpaDados();
        for (quadrado = 1; quadrado < 10; quadrado++) {
            pesqLimQuad(quadrado);
            for (int n = 1; n < 10; n++) {
                pesqEspVazio();
                gabaMatriz[getPlq() + getLinha()][getPcq() + getColuna()] = n;
                vrl = pesqLinhaRep(getPlq() + getLinha());
                vrc =  pesqColunaRep(getPcq() + getColuna());
                tentativas = 0;
                while (vrl || vrc) {
                   if (tentativas == 50) {
                        if (quadrado == ultQuadRep) {
                            if (voltar < quadrado) {
                                voltar++;
                            }
                            break;
                        } else {
                            voltar = 1;
                            ultQuadRep = quadrado;
                            break;
                        }
                    }
                    tentativas++;
                    gabaMatriz[getPlq() + getLinha()][getPcq() + getColuna()] = 0;
                    pesqEspVazio();
                    gabaMatriz[getPlq() + getLinha()][getPcq() + getColuna()] = n;
                    vrl = pesqLinhaRep(getPlq() + getLinha());
                    vrc =  pesqColunaRep(getPcq() + getColuna());
                }
                //limpa quadrado que está com erro
                if (tentativas == 50) {
                    for (int q = quadrado; q > quadrado - voltar; q--) {
                        pesqLimQuad(q);
                        limpaQuad();
                    }
                    quadrado -= voltar;
                    break;
                }
            }
        }
    }
    
    void imprimisud(){
        
        JOptionPane.showConfirmDialog
                (null, " " + gabaMatriz[0][0] + " " + gabaMatriz[0][1] + " " + gabaMatriz[0][2] + "     " + gabaMatriz[0][3] + " " + gabaMatriz[0][4] + " " + gabaMatriz[0][5] + "     " + gabaMatriz[0][6] + " " + gabaMatriz[0][7] + " " + gabaMatriz[0][8]+
                     "\n " + gabaMatriz[1][0] + " " + gabaMatriz[1][1] + " " + gabaMatriz[1][2] + "     " + gabaMatriz[1][3] + " " + gabaMatriz[1][4] + " " + gabaMatriz[1][5] + "     " + gabaMatriz[1][6] + " " + gabaMatriz[1][7] + " " + gabaMatriz[1][8]+
                     "\n " + gabaMatriz[2][0] + " " + gabaMatriz[2][1] + " " + gabaMatriz[2][2] + "     " + gabaMatriz[2][3] + " " + gabaMatriz[2][4] + " " + gabaMatriz[2][5] + "     " + gabaMatriz[2][6] + " " + gabaMatriz[2][7] + " " + gabaMatriz[2][8]+
                   "\n\n " + gabaMatriz[3][0] + " " + gabaMatriz[3][1] + " " + gabaMatriz[3][2] + "     " + gabaMatriz[3][3] + " " + gabaMatriz[3][4] + " " + gabaMatriz[3][5] + "     " + gabaMatriz[3][6] + " " + gabaMatriz[3][7] + " " + gabaMatriz[3][8]+
                     "\n " + gabaMatriz[4][0] + " " + gabaMatriz[4][1] + " " + gabaMatriz[4][2] + "     " + gabaMatriz[4][3] + " " + gabaMatriz[4][4] + " " + gabaMatriz[4][5] + "     " + gabaMatriz[4][6] + " " + gabaMatriz[4][7] + " " + gabaMatriz[4][8]+
                     "\n " + gabaMatriz[5][0] + " " + gabaMatriz[5][1] + " " + gabaMatriz[5][2] + "     " + gabaMatriz[5][3] + " " + gabaMatriz[5][4] + " " + gabaMatriz[5][5] + "     " + gabaMatriz[5][6] + " " + gabaMatriz[5][7] + " " + gabaMatriz[5][8]+
                   "\n\n " + gabaMatriz[6][0] + " " + gabaMatriz[6][1] + " " + gabaMatriz[6][2] + "     " + gabaMatriz[6][3] + " " + gabaMatriz[6][4] + " " + gabaMatriz[6][5] + "     " + gabaMatriz[6][6] + " " + gabaMatriz[6][7] + " " + gabaMatriz[6][8]+
                     "\n " + gabaMatriz[7][0] + " " + gabaMatriz[7][1] + " " + gabaMatriz[7][2] + "     " + gabaMatriz[7][3] + " " + gabaMatriz[7][4] + " " + gabaMatriz[7][5] + "     " + gabaMatriz[7][6] + " " + gabaMatriz[7][7] + " " + gabaMatriz[7][8]+
                     "\n " + gabaMatriz[8][0] + " " + gabaMatriz[8][1] + " " + gabaMatriz[8][2] + "     " + gabaMatriz[8][3] + " " + gabaMatriz[8][4] + " " + gabaMatriz[8][5] + "     " + gabaMatriz[8][6] + " " + gabaMatriz[8][7] + " " + gabaMatriz[8][8], "Resposta", -1);
    }
    
    void esconderNumeros(int quantidade) {
        int l, c;

        for (l = 0; l < 9; l++) {
            for (c = 0; c < 9; c++) {
                respMatriz[l][c] = gabaMatriz[l][c];
            }
        }

        for (int q = 0; q < quantidade; q++) {
            l = Math.abs(varRand.nextInt() % 9);
            c = Math.abs(varRand.nextInt() % 9);
            while (respMatriz[l][c] == 0) {
                l = Math.abs(varRand.nextInt() % 9);
                c = Math.abs(varRand.nextInt() % 9);
            }
            respMatriz[l][c] = 0;
        }
    }

    int [][] jogar(int quantidadeBrancos) {
        limpaDados();
        salvaNumeros();
        esconderNumeros(quantidadeBrancos);

        return respMatriz;
    }

    String validar(String[][] mSt) {
        int contBranco = 0, contErros = 0;
        int mVal[][] = new int[9][9];//matriz com as respostas do usuario
        String resp = "";

        for (int l = 0; l < 9; l++) {
            for (int c = 0; c < 9; c++) {
                if (mSt[l][c].compareTo("") == 0) {
                    contBranco++;
                    mVal[l][c] = 0;//matriz com as respostas do usuario
                } else {
                    try {
                        mVal[l][c] = Integer.parseInt(mSt[l][c]);
                    } catch (NumberFormatException nfe) {
                        resp += "\n Existe um caracter ilegal na linha " + (l+1) + " coluna " + (c+1) + ".";
                    }
                }
            }
        }

        if (contBranco != 0) {
            resp += "\n Voce deixou " + contBranco + " espacos em branco.";
        }

        // verifica se ha erros
        // pode haver mais de uma resposta; e a resposta do usuario 
        // pode ser diferente da matriz resposta
        boolean erro = false;
        for (int l = 0; l < 9 && erro == false; l++) {
            erro = pesqLinhaRep(l);
        }

        for (int c = 0; c < 9 && erro == false; c++) {
            erro =  pesqColunaRep(c);
        }

        for (int q = 0; q < 9 && erro == false; q++) {
            erro = pesqQuadRep(q);
        }

        // encontra o local do erro
        for (int l = 0; l < 9 && erro == true; l++) {
            for (int c = 0; c < 9; c++) {
                if (mVal[l][c] != gabaMatriz[l][c] && mVal[l][c]!=0) {
                    contErros++;
                    resp += "\n Erro na linha " +(l+1) + " coluna " + (c+1) + ".";
                }
            }
        }
        if (contErros != 0) {
            resp += "\n Total de erros: " + contErros + ".";
        }
        return resp;


    }

    /**
     * @return the plq
     */
    public int getPlq() {
        return plq;
    }

    /**
     * @param plq the plq to set
     */
    public void setPlq(int plq) {
        this.plq = plq;
    }

    /**
     * @return the pcq
     */
    public int getPcq() {
        return pcq;
    }

    /**
     * @param pcq the pcq to set
     */
    public void setPcq(int pcq) {
        this.pcq = pcq;
    }

    /**
     * @return the ulq
     */
    public int getUlq() {
        return ulq;
    }

    /**
     * @param ulq the ulq to set
     */
    public void setUlq(int ulq) {
        this.ulq = ulq;
    }

    /**
     * @return the ucq
     */
    public int getUcq() {
        return ucq;
    }

    /**
     * @param ucq the ucq to set
     */
    public void setUcq(int ucq) {
        this.ucq = ucq;
    }

    /**
     * @return the linha
     */
    public int getLinha() {
        return linha;
    }

    /**
     * @param linha the linha to set
     */
    public void setLinha(int linha) {
        this.linha = linha;
    }

    /**
     * @return the coluna
     */
    public int getColuna() {
        return coluna;
    }

    /**
     * @param coluna the coluna to set
     */
    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}