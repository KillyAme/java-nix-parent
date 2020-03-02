package com.alevel.java.nix.module1;


public class GameOfLife {
    public static int[][] fillingWithStartingElements(int row, int column) {
        int[][] array = new int[row][column];
        for (int i = 0, length = array.length; i < length; i++) {
            for (int j = 0, length1 = array[i].length; j < length1; j++) {
                array[i][j] = (int) (Math.random() * 2);
            }
        }
        return array;
    }

    public void oneTurnOfTheGame(int row, int column) {
        int[][] arrayStart = fillingWithStartingElements(row, column);
        for (int[] ints : arrayStart) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        int[][] arrayAfter = new int[row][column];
        for (int i = 1; i < arrayStart.length - 1; i++) {
            for (int j = 1; j < arrayStart[i].length - 1; j++) {
                int countAlive = 0;
                if (arrayStart[i][j] == 1) {
                    for (int iMinus1 = -1; iMinus1 <= 1; iMinus1++) {
                        for (int jMinus1 = -1; jMinus1 <= 1; jMinus1++) {
                            if (arrayStart[i - iMinus1][j - jMinus1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    countAlive--;
                    System.out.print("-");
                    if (countAlive < 2) {
                        arrayAfter[i][j] = 0;
                    }
                    if (countAlive >= 2 && countAlive <= 3) {
                        arrayAfter[i][j] = 1;
                    }
                    if (countAlive > 3) {
                        arrayAfter[i][j] = 0;
                    }
                } else {
                    for (int iMinus1 = -1; iMinus1 <= 1; iMinus1++) {
                        for (int jMinus1 = -1; jMinus1 <= 1; jMinus1++) {
                            if (arrayStart[i - iMinus1][j - jMinus1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    System.out.print("-");
                    if (countAlive == 3)
                        arrayAfter[i][j] = 1;
                }
            }
        }

        {
            int countAlive = 0;
            if (arrayStart[0][1] == 1) countAlive++;
            if (arrayStart[1][0] == 1) countAlive++;
            if (arrayStart[1][1] == 1) countAlive++;
            if (arrayStart[0][0] == 1) {
                if (countAlive < 2) {
                    arrayAfter[0][0] = 0;
                }
                if (countAlive >= 2) {
                    arrayAfter[0][0] = 1;
                }
            } else {
                if (countAlive == 3) {
                    arrayAfter[0][0] = 1;
                } else {
                    arrayAfter[0][0] = 0;
                }
            }
        }
        {
            int countAlive = 0;
            if (arrayStart[arrayStart.length - 2][0] == 1) countAlive++;
            if (arrayStart[arrayStart.length - 2][1] == 1) countAlive++;
            if (arrayStart[arrayStart.length - 1][1] == 1) countAlive++;
            if (arrayStart[arrayStart.length - 1][0] == 1) {
                if (countAlive < 2) {
                    arrayAfter[arrayStart.length - 1][0] = 0;
                }
                if (countAlive >= 2) {
                    arrayAfter[arrayStart.length - 1][0] = 1;
                }
            } else {
                if (countAlive == 3) {
                    arrayAfter[arrayStart.length - 1][0] = 1;
                } else {
                    arrayAfter[arrayStart.length - 1][0] = 0;
                }
            }
        }
        {
            int countAlive = 0;
            if (arrayStart[0][arrayStart[0].length - 2] == 1) countAlive++;
            if (arrayStart[1][arrayStart[1].length - 2] == 1) countAlive++;
            if (arrayStart[1][arrayStart[1].length - 1] == 1) countAlive++;
            if (arrayStart[0][arrayStart[0].length - 1] == 1) {
                if (countAlive < 2) {
                    arrayAfter[0][arrayStart[0].length - 1] = 0;
                }
                if (countAlive >= 2) {
                    arrayAfter[0][arrayStart[0].length - 1] = 1;
                }
            } else {
                if (countAlive == 3) {
                    arrayAfter[0][arrayStart[0].length - 1] = 1;
                } else {
                    arrayAfter[0][arrayStart[0].length - 1] = 0;
                }
            }
        }
        {
            int countAlive = 0;
            if (arrayStart[arrayStart.length - 2][arrayStart[0].length - 1] == 1) countAlive++;
            if (arrayStart[arrayStart.length - 2][arrayStart[0].length - 2] == 1) countAlive++;
            if (arrayStart[arrayStart.length - 1][arrayStart[0].length - 2] == 1) countAlive++;
            if (arrayStart[arrayStart.length - 1][arrayStart[0].length - 1] == 1) {
                if (countAlive < 2) {
                    arrayAfter[arrayStart.length - 1][arrayStart[0].length - 1] = 0;
                }
                if (countAlive >= 2) {
                    arrayAfter[arrayStart.length - 1][arrayStart[0].length - 1] = 1;
                }
            } else {
                if (countAlive == 3) {
                    arrayAfter[arrayStart.length - 1][arrayStart[0].length - 1] = 1;
                } else {
                    arrayAfter[arrayStart.length - 1][arrayStart[0].length - 1] = 0;
                }
            }
        }
        {
            for (int i = 1; i < arrayStart[0].length - 1; i++) {
                int countAlive = 0;
                if (arrayStart[0][i] == 1) {
                    for (int i1 = 0; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            if (arrayStart[i1][i + j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    countAlive--;
                    if (countAlive < 2) {
                        arrayAfter[0][i] = 0;
                    }
                    if (countAlive >= 2 && countAlive <= 3) {
                        arrayAfter[0][i] = 1;
                    }
                    if (countAlive > 3) {
                        arrayAfter[0][i] = 0;
                    }
                } else {
                    for (int i1 = 0; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            if (arrayStart[i1][i + j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    if (countAlive == 3) {
                        arrayAfter[0][i] = 1;
                    }
                }
            }
        }
        {
            for (int i = 1; i < arrayStart.length - 1; i++) {
                int countAlive = 0;
                if (arrayStart[i][0] == 1) {
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = 0; j1 <= 1; j1++) {
                            if (arrayStart[i + i1][j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    countAlive--;
                    if (countAlive < 2) {
                        arrayAfter[i][0] = 0;
                    }
                    if (countAlive >= 2 && countAlive <= 3) {
                        arrayAfter[i][0] = 1;
                    }
                    if (countAlive > 3) {
                        arrayAfter[i][0] = 0;
                    }
                } else {
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = 0; j1 <= 1; j1++) {
                            if (arrayStart[i + i1][j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    if (countAlive == 3) {
                        arrayAfter[i][0] = 1;
                    }
                }
            }
        }
        {
            for (int i = 1; i < arrayStart.length - 1; i++) {
                int countAlive = 0;
                if (arrayStart[i][arrayStart[i].length - 1] == 1) {
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 0; j1++) {
                            if (arrayStart[i + i1][arrayStart[i].length - 1 + j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    countAlive--;
                    if (countAlive < 2) {
                        arrayAfter[i][arrayStart[i].length - 1] = 0;
                    }
                    if (countAlive >= 2 && countAlive <= 3) {
                        arrayAfter[i][arrayStart[i].length - 1] = 1;
                    }
                    if (countAlive > 3) {
                        arrayAfter[i][arrayStart[i].length - 1] = 0;
                    }
                } else {
                    for (int i1 = -1; i1 <= 1; i1++) {
                        for (int j1 = -1; j1 <= 0; j1++) {
                            if (arrayStart[i + i1][arrayStart[i].length - 1 + j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    if (countAlive == 3) {
                        arrayAfter[i][arrayStart[i].length - 1] = 1;
                    }
                }
            }
        }
        {
            for (int i = 1; i < arrayStart[0].length - 1; i++) {
                int countAlive = 0;
                if (arrayStart[arrayStart.length - 1][i] == 1) {
                    for (int i1 = -1; i1 <= 0; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            if (arrayStart[arrayStart.length - 1 + i1][i + j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    countAlive--;
                    if (countAlive < 2) {
                        arrayAfter[arrayStart.length - 1][i] = 0;
                    }
                    if (countAlive >= 2 && countAlive <= 3) {
                        arrayAfter[arrayStart.length - 1][i] = 1;
                    }
                    if (countAlive > 3) {
                        arrayAfter[arrayStart.length - 1][i] = 0;
                    }
                } else {
                    for (int i1 = -1; i1 <= 0; i1++) {
                        for (int j1 = -1; j1 <= 1; j1++) {
                            if (arrayStart[arrayStart.length - 1 + i1][i + j1] == 1) {
                                countAlive++;
                            }
                        }
                    }
                    if (countAlive == 3) {
                        arrayAfter[arrayStart.length - 1][i] = 1;
                    }
                }
            }
        }

        System.out.println();
        for (int[] ints : arrayAfter) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.oneTurnOfTheGame(10, 10);
    }
}
