package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

public class NQueenProblem {
    int N;
    Context context;
    NQueenProblem(){

    }
    public NQueenProblem(int parseInt, Context context) {
        N = parseInt;
        this.context = context;
    }

    void printSolution(String output,Context context)
    {
        this.context=context;
        int N=(int)Math.sqrt(output.length());

        int [][]board=new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++) {
                board[i][j]=output.charAt(i*N+j)-'0';
            }
        }

        TextView t;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j]!=0){
                    int id = Integer.parseInt(String.valueOf(i+1)+String.valueOf(j+1));
                    t = ((Activity)context).findViewById(id);
                    t.setBackgroundColor(Color.parseColor("#FFFF00"));
                    //the suitable place for queen is shown by green color
                }
            }
        }
    }
    String code=""+
    "boolean isSafe(int[][] board, int row, int col)"+
    "{"+
        "int i, j;"+

        /* Check this row on left side */
        "for (i = 0; i < col; i++)"+
            "if (board[row][i] == 1)"+
                "return false;"+

        /* Check upper diagonal on left side */
        "for (i = row, j = col; i >= 0 && j >= 0; i--, j--)"+
            "if (board[i][j] == 1)"+
                "return false;"+

        /* Check lower diagonal on left side */
        "for (i = row, j = col; j >= 0 && i < N; i++, j--)"+
            "if (board[i][j] == 1)"+
                "return false;"+

        "return true;"+
    "}"+

    //A recursive utility function to solve N Queen problem
    "boolean solveNQUtil(int[][] board, int col)"+
    "{"+
        /* base case: If all queens are placed
           then return true */
        "if (col >= N)"+
            "return true;"+

        /* Consider this column and try placing
           this queen in all rows one by one */
        "for (int i = 0; i < N; i++)"+
        "{"+
            /* Check if the queen can be placed on
               board[i][col] */
            "if (isSafe(board, i, col))"+
            "{"+
                /* Place this queen in board[i][col] */
                "board[i][col] = 1;"+

                /* recur to place rest of the queens */
                "if (solveNQUtil(board, col + 1))"+
                    "return true;"+

                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
                "board[i][col] = 0;"+
            "}"+
        "}"+

        /* If the queen can not be placed in any row in
           this colum col, then return false */
        "return false;"+
    "}"+

    /* This function solves the N Queen problem using
       Backtracking.  It mainly uses solveNQUtil () to
       solve the problem. It returns false if queens
       cannot be placed, otherwise, return true and
       prints placement of queens in the form of 1s.
       Please note that there may be more than one
       solutions, this function prints one of the
       feasible solutions.*/
    "boolean solveNQ()"+
    "{"+
        "int[][] board = new int[N][N];"+
        "for (int a = 0;a<N;a++){"+
            "for (int b=0;b<N;b++){"+
                "board[a][b]=0;"+
            "}"+
        "}"+

        "if (!solveNQUtil(board, 0)) {"+
            "return false;"+
        "}"+
        "else{"+
            "printSolution(board);"+
        "}"+
        "return true;"+
    "}"+
    "void printSolution(int board[][])"+
    "{"+
        "for (int i = 0; i < N; i++) {"+
            "for (int j = 0; j < N; j++)"+
                "System.out.print(board[i][j]);"+
        "}"+
    "}";
    void solveNQ() throws JSONException {
        OffloadCode offload = new OffloadCode(code, N + "",context);
        String output=offload.getConnection();

    }
}