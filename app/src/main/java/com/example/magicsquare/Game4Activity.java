package com.example.magicsquare;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game4Activity extends AppCompatActivity implements OnClickListener {
    private static int[][] matrix;
    private static int[][] newMatrix;
    private static Random random;
    private static int suma;
    private Button validateButton;

    Button backButton;
    Button newGameButton;

    public void onClick(View v) {
        if (v == backButton) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if (v.getId() == R.id.newgame) {
            initializeGame();
        }else if (v.getId() == R.id.validate) {
            initializeGame();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);

        newGameButton = findViewById(R.id.newgame);
        newGameButton.setOnClickListener(this);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(this);

        validateButton = findViewById(R.id.validate);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateMatrix();
            }
        });

        random = new Random();

        initializeGame();
    }


    public void initializeGame() {
        EditText cell1 = findViewById(R.id.cell1);
        EditText cell2 = findViewById(R.id.cell2);
        EditText cell3 = findViewById(R.id.cell3);
        EditText cell4 = findViewById(R.id.cell4);
        EditText cell5 = findViewById(R.id.cell5);
        EditText cell6 = findViewById(R.id.cell6);
        EditText cell7 = findViewById(R.id.cell7);
        EditText cell8 = findViewById(R.id.cell8);
        EditText cell9 = findViewById(R.id.cell9);
        EditText cell10 = findViewById(R.id.cell10);
        EditText cell11 = findViewById(R.id.cell11);
        EditText cell12 = findViewById(R.id.cell12);
        EditText cell13 = findViewById(R.id.cell13);
        EditText cell14 = findViewById(R.id.cell14);
        EditText cell15 = findViewById(R.id.cell15);
        EditText cell16 = findViewById(R.id.cell16);

        TextView sumValue = findViewById(R.id.sumValue);

        matrix = new int[4][4];
        newMatrix = new int[4][4];
        generateMatrix(matrix);
        sumValue.setText("Искомая сумма: " + String.valueOf(suma));

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }

        List<int[]> indices = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                indices.add(new int[]{i, j});
            }
        }

        List<int[]> randomIndices = new ArrayList<>();
        while (randomIndices.size() < 6) {
            int[] index = indices.get(random.nextInt(indices.size()));
            if (!randomIndices.contains(index)) {
                randomIndices.add(index);
            }
        }

        for (int[] index : randomIndices) {
            matrix[index[0]][index[1]] = 0;
        }

        cell1.setText(String.valueOf(matrix[0][0]));
        cell2.setText(String.valueOf(matrix[0][1]));
        cell3.setText(String.valueOf(matrix[0][2]));
        cell4.setText(String.valueOf(matrix[0][3]));
        cell5.setText(String.valueOf(matrix[1][0]));
        cell6.setText(String.valueOf(matrix[1][1]));
        cell7.setText(String.valueOf(matrix[1][2]));
        cell8.setText(String.valueOf(matrix[1][3]));
        cell9.setText(String.valueOf(matrix[2][0]));
        cell10.setText(String.valueOf(matrix[2][1]));
        cell11.setText(String.valueOf(matrix[2][2]));
        cell12.setText(String.valueOf(matrix[2][3]));
        cell13.setText(String.valueOf(matrix[3][0]));
        cell14.setText(String.valueOf(matrix[3][1]));
        cell15.setText(String.valueOf(matrix[3][2]));
        cell16.setText(String.valueOf(matrix[3][3]));
    }



    private static void generateMatrix(int[][] matrix) {
        int k = 200;
        suma = getRandomNumberInRange(34, 100);


        List<Integer> mas = new ArrayList<>();
        for (int num = 0; num < suma - 3; num++) {
            mas.add(num);
        }

        int[] combination = generateCombination(suma, mas);
        matrix[0][0] = combination[0];
        matrix[1][1] = combination[1];
        matrix[2][2] = combination[2];
        matrix[3][3] = combination[3];

        combination = generateCombination(suma, mas);
        matrix[0][3] = combination[0];
        matrix[1][2] = combination[1];
        matrix[2][1] = combination[2];
        matrix[3][0] = combination[3];

        while (k > 1) {
            k--;
            matrix[0][1] = mas.get(random.nextInt(mas.size()));
            matrix[0][2] = mas.get(random.nextInt(mas.size()));
            if (sum(matrix[0]) == suma) {
                mas.remove(Integer.valueOf(matrix[0][1]));
                mas.remove(Integer.valueOf(matrix[0][2]));
                break;
            }
        }

        while (k > 1) {
            k--;
            matrix[1][0] = mas.get(random.nextInt(mas.size()));
            matrix[2][0] = mas.get(random.nextInt(mas.size()));
            if (matrix[0][0] + matrix[1][0] + matrix[2][0] + matrix[3][0] == suma) {
                mas.remove(Integer.valueOf(matrix[1][0]));
                mas.remove(Integer.valueOf(matrix[2][0]));
                break;
            }
        }

        while (k > 1) {
            k--;
            matrix[1][3] = mas.get(random.nextInt(mas.size()));
            matrix[2][3] = mas.get(random.nextInt(mas.size()));
            if (matrix[0][3] + matrix[1][3] + matrix[2][3] + matrix[3][3] == suma) {
                mas.remove(Integer.valueOf(matrix[1][3]));
                mas.remove(Integer.valueOf(matrix[2][3]));
                break;
            }
        }
        while (k > 1) {
            k--;
            matrix[3][1] = mas.get(random.nextInt(mas.size()));
            matrix[3][2] = mas.get(random.nextInt(mas.size()));
            if (sum(matrix[3]) == suma) {
                mas.remove(Integer.valueOf(matrix[3][1]));
                mas.remove(Integer.valueOf(matrix[3][2]));
                break;
            }
        }

        if (k == 1) {
            k = 200;
            generateMatrix(matrix);
        }
    }

    private static int[] generateCombination(int targetSum, List<Integer> mas) {
        int[] combination = new int[4];

        while (sum(combination) != targetSum) {
            for (int i = 0; i < combination.length; i++) {
                combination[i] = mas.get(random.nextInt(mas.size()));
            }
        }

        for (int value : combination) {
            mas.remove(Integer.valueOf(value));
        }

        return combination;
    }

    private static int[] genCom(int targetSum, List<Integer> mas) {
        int[] combination = new int[2];

        while (sum(combination) + matrix[0][0] + matrix[0][3] != targetSum) {
            for (int i = 0; i < combination.length; i++) {
                combination[i] = mas.get(random.nextInt(mas.size()));
            }
        }

        for (int value : combination) {
            mas.remove(Integer.valueOf(value));
        }

        return combination;
    }

    private static int sum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public void validateMatrix(){
        EditText cell1 = findViewById(R.id.cell1);
        EditText cell2 = findViewById(R.id.cell2);
        EditText cell3 = findViewById(R.id.cell3);
        EditText cell4 = findViewById(R.id.cell4);
        EditText cell5 = findViewById(R.id.cell5);
        EditText cell6 = findViewById(R.id.cell6);
        EditText cell7 = findViewById(R.id.cell7);
        EditText cell8 = findViewById(R.id.cell8);
        EditText cell9 = findViewById(R.id.cell9);
        EditText cell10 = findViewById(R.id.cell10);
        EditText cell11 = findViewById(R.id.cell11);
        EditText cell12 = findViewById(R.id.cell12);
        EditText cell13 = findViewById(R.id.cell13);
        EditText cell14 = findViewById(R.id.cell14);
        EditText cell15 = findViewById(R.id.cell15);
        EditText cell16 = findViewById(R.id.cell16);

        int cellValue1 = Integer.parseInt(cell1.getText().toString());
        int cellValue2 = Integer.parseInt(cell2.getText().toString());
        int cellValue3 = Integer.parseInt(cell3.getText().toString());
        int cellValue4 = Integer.parseInt(cell4.getText().toString());
        int cellValue5 = Integer.parseInt(cell5.getText().toString());
        int cellValue6 = Integer.parseInt(cell6.getText().toString());
        int cellValue7 = Integer.parseInt(cell7.getText().toString());
        int cellValue8 = Integer.parseInt(cell8.getText().toString());
        int cellValue9 = Integer.parseInt(cell9.getText().toString());
        int cellValue10 = Integer.parseInt(cell10.getText().toString());
        int cellValue11 = Integer.parseInt(cell11.getText().toString());
        int cellValue12 = Integer.parseInt(cell12.getText().toString());
        int cellValue13 = Integer.parseInt(cell13.getText().toString());
        int cellValue14 = Integer.parseInt(cell14.getText().toString());
        int cellValue15 = Integer.parseInt(cell15.getText().toString());
        int cellValue16 = Integer.parseInt(cell16.getText().toString());

        // Заполнение newMatrix значениями из ячеек cell
        matrix[0][0] = cellValue1;
        matrix[0][1] = cellValue2;
        matrix[0][2] = cellValue3;
        matrix[0][3] = cellValue4;
        matrix[1][0] = cellValue5;
        matrix[1][1] = cellValue6;
        matrix[1][2] = cellValue7;
        matrix[1][3] = cellValue8;
        matrix[2][0] = cellValue9;
        matrix[2][1] = cellValue10;
        matrix[2][2] = cellValue11;
        matrix[2][3] = cellValue12;
        matrix[3][0] = cellValue13;
        matrix[3][1] = cellValue14;
        matrix[3][2] = cellValue15;
        matrix[3][3] = cellValue16;

        boolean isMagicSquare = true;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != newMatrix[i][j]) {
                    isMagicSquare = false;
                    break;
                }
            }
            if (!isMagicSquare) {
                break;
            }
        }
        if (isMagicSquare) {
            Toast.makeText(this, "Это магический квадрат!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Это не магический квадрат", Toast.LENGTH_SHORT).show();
        }
    }


}