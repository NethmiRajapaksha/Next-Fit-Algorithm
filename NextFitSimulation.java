/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.nextfitsimulation;

/**
 *
 * @author Game Zone Tech
 */


import java.util.Scanner;

public class NextFitSimulation {
    static class Block {
        int size;
        boolean isFree;

        Block(int size) {
            this.size = size;
            this.isFree = true;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for memory blocks
        System.out.print("Enter the number of memory blocks: ");
        int numBlocks = scanner.nextInt();
        Block[] memoryBlocks = new Block[numBlocks];

        System.out.println("Enter the sizes of the memory blocks (in KB):");
        for (int i = 0; i < numBlocks; i++) {
            System.out.printf("Block %d size: ", i + 1);
            memoryBlocks[i] = new Block(scanner.nextInt());
        }

        // Input for processes
        System.out.print("Enter the number of processes: ");
        int numProcesses = scanner.nextInt();
        int[] processSizes = new int[numProcesses];

        System.out.println("Enter the sizes of the processes (in KB):");
        for (int i = 0; i < numProcesses; i++) {
            System.out.printf("Process %c size: ", 'A' + i);
            processSizes[i] = scanner.nextInt();
        }

        // Perform allocation using Next Fit
        int pointer = 0; // Start searching from the beginning
        for (int i = 0; i < numProcesses; i++) {
            int processSize = processSizes[i];
            boolean allocated = false;

            for (int j = 0; j < memoryBlocks.length; j++) {
                int index = (pointer + j) % memoryBlocks.length; // Loop around the blocks
                if (memoryBlocks[index].isFree && memoryBlocks[index].size >= processSize) {
                    // Allocate the process
                    memoryBlocks[index].size -= processSize;
                    memoryBlocks[index].isFree = memoryBlocks[index].size > 0;
                    System.out.printf("Process %c allocated %d KB in Block %d.%n",
                            'A' + i, processSize, index + 1);
                    pointer = index; // Update pointer to the current block
                    allocated = true;
                    break;
                }
            }

            if (!allocated) {
                System.out.printf("Process %c could not be allocated.%n", 'A' + i);
            }
        }

        // Print final memory state
        System.out.println("\nFinal Memory State:");
        for (int i = 0; i < memoryBlocks.length; i++) {
            System.out.printf("Block %d: %d KB (%s)%n",
                    i + 1, memoryBlocks[i].size, memoryBlocks[i].isFree ? "free" : "allocated");
        }

        scanner.close();
    }
}
