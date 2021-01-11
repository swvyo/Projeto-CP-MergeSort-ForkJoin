package edu.br.cp.mergesort.forkjoin;

import java.util.concurrent.RecursiveAction;

public class MergeSortFK extends RecursiveAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] array; //array que será ordenado
    private int inicio; //índice de início
    private int fim; //íncide do fim
    
    //ordena o subarray do índice 'inicio' até 'fim'
    public MergeSortFK(int[] array, int inicio, int fim){
        this.array = array;
        this.inicio = inicio;
        this.fim = fim;
    }
    //ordena o array por completo
    public MergeSortFK(int[] array){
        this(array, 0, array.length - 1);
    }
    //executa o MergeSort paralelamente com Fork-Join
    @Override
    protected void compute() {
        if(inicio < fim){
            int meio = (inicio + fim) / 2; //calcula o meio
            //chamadas recursivas paralelamente(fork)
            invokeAll(new MergeSortFK(array, inicio, meio),
                      new MergeSortFK(array, meio + 1, fim));
            merge(inicio, meio, fim); //intercala os subvetores(join)
        }
    }
    //intercala os subvetores esquerdo e direito
    private void merge(int inicio, int meio, int fim){
        int tamEsq = meio - inicio + 1;
        int tamDir = fim - meio; 
        int esq[] = new int[tamEsq]; 
        int dir[] = new int[tamDir]; 
        int idxEsq = 0; 
        int idxDir = 0; 
        
        //Copia os elementos do subvetor esquerdo para o vetor auxiliar
        for(int i = 0; i < tamEsq; i++){
            esq[i] = array[inicio + i];
        }
        //Copia os elementos do subvetor direito para o vetor auxiliar
        for(int j = 0; j < tamDir; j++){
            dir[j] = array[meio + 1 + j];
        }
        
        //intercala os subvetores
        for(int k = inicio; k <= fim; k++){
            if(idxEsq < tamEsq){
                if(idxDir < tamDir){
                    if(esq[idxEsq] < dir[idxDir]){
                        array[k] = esq[idxEsq++];
                    }else{
                        array[k] = dir[idxDir++];
                    }
                }else{
                    array[k] = esq[idxEsq++];
                }
            }else{
                array[k] = dir[idxDir++];
            }
        }
    }
}
