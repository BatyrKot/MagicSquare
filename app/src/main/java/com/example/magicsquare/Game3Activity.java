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

public class Game3Activity extends AppCompatActivity implements OnClickListener {
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
        setContentView(R.layout.activity_game3);

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
        TextView sumValue = findViewById(R.id.sumValue);

        matrix = new int[3][3];
        newMatrix = new int[3][3];
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
        while (randomIndices.size() < 4) {
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
        cell4.setText(String.valueOf(matrix[1][0]));
        cell5.setText(String.valueOf(matrix[1][1]));
        cell6.setText(String.valueOf(matrix[1][2]));
        cell7.setText(String.valueOf(matrix[2][0]));
        cell8.setText(String.valueOf(matrix[2][1]));
        cell9.setText(String.valueOf(matrix[2][2]));
    }



    private static void generateMatrix(int[][] matrix) {
        int k = 200;
        suma = getRandomNumberInRange(12, 100);


        List<Integer> mas = new ArrayList<>();
        for (int num = 0; num < suma - 3; num++) {
            mas.add(num);
        }

        int[] combination = generateCombination(suma, mas);
        matrix[0][0] = combination[0];
        matrix[1][1] = combination[1];
        matrix[2][2] = combination[2];

        combination = genCom(suma, mas);
        matrix[0][2] = combination[0];
        matrix[2][0] = combination[1];

        while (k > 1) {
            k--;
            matrix[1][2] = mas.get(random.nextInt(mas.size()));
            if (matrix[0][2] + matrix[1][2] + matrix[2][2] == suma) {
                mas.remove(Integer.valueOf(matrix[1][2]));
                break;
            }
        }

        while (k > 1) {
            k--;
            matrix[0][1] = mas.get(random.nextInt(mas.size()));
            if (matrix[0][0] + matrix[0][1] + matrix[0][2] == suma) {
                mas.remove(Integer.valueOf(matrix[0][1]));
                break;
            }
        }

        while (k > 1) {
            k--;
            matrix[1][0] = mas.get(random.nextInt(mas.size()));
            if (matrix[1][0] + matrix[0][0] + matrix[2][0] == suma) {
                mas.remove(Integer.valueOf(matrix[1][0]));
                break;
            }
        }

        while (k > 1) {
            k--;
            matrix[2][1] = mas.get(random.nextInt(mas.size()));
            if (matrix[2][0] + matrix[2][2] + matrix[2][1] == suma) {
                mas.remove(Integer.valueOf(matrix[2][1]));
                break;
            }
        }

        if (k == 1) {
            k = 200;
            generateMatrix(matrix);
        }
    }

    private static int[] generateCombination(int targetSum, List<Integer> mas) {
        int[] combination = new int[3];

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

        while (sum(combination) + matrix[1][1] != targetSum) {
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

        int cellValue1 = Integer.parseInt(cell1.getText().toString());
        int cellValue2 = Integer.parseInt(cell2.getText().toString());
        int cellValue3 = Integer.parseInt(cell3.getText().toString());
        int cellValue4 = Integer.parseInt(cell4.getText().toString());
        int cellValue5 = Integer.parseInt(cell5.getText().toString());
        int cellValue6 = Integer.parseInt(cell6.getText().toString());
        int cellValue7 = Integer.parseInt(cell7.getText().toString());
        int cellValue8 = Integer.parseInt(cell8.getText().toString());
        int cellValue9 = Integer.parseInt(cell9.getText().toString());

        // Заполнение newMatrix значениями из ячеек cell
        matrix[0][0] = cellValue1;
        matrix[0][1] = cellValue2;
        matrix[0][2] = cellValue3;
        matrix[1][0] = cellValue4;
        matrix[1][1] = cellValue5;
        matrix[1][2] = cellValue6;
        matrix[2][0] = cellValue7;
        matrix[2][1] = cellValue8;
        matrix[2][2] = cellValue9;

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