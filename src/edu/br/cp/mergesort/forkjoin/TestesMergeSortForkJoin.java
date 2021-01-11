package edu.br.cp.mergesort.forkjoin;

import java.util.concurrent.ForkJoinPool;

//realiza testes utilizando o fork/join
public class TestesMergeSortForkJoin {
  public static void main(String[] args) {        
     
	  ForkJoinPool pool = new ForkJoinPool(); //cria um pool de threads Fork/Join
      int[] A = {5, 20, 7, 6, 300, 1, 175, 3, -90, 4}; //array que será ordenado contendo os 10 valores
      
      //imprime o vetor desordenado
      System.out.printf("A (desordenado) = [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n",
              A[0], A[1], A[2], A[3], A[4], A[5], A[6], A[7], A[8], A[9]);        
      
      MergeSortFK merge = new MergeSortFK(A); //tarefa ForkJoin para ordenar o vetor A
      pool.invoke(merge); //executa a tarefa utilizando o pool
      
      //imprime o vetor ordenado
      System.out.printf("A (ordenado) = [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n",
              A[0], A[1], A[2], A[3], A[4], A[5], A[6], A[7], A[8], A[9]);
  }   
}