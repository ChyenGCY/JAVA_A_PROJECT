package AIPlayer;

import controller.ChessBoard;
import controller.GameRule;
import model.ChessPiece;
import view.GameFrame;

public class EasyAI implements Runnable {

    private ChessPiece chessPiece;
    private int[][] mapPoint = {
            { 90, -60, 10, 10, 10, 10, -60, 90 },
            { -60, -80, 5, 5, 5, 5, -80, -60 },
            { 10, 5, 1, 1, 1, 1, 5, 10 },
            { 10, 5, 1, 1, 1, 1, 5, 10 },
            { 10, 5, 1, 1, 1, 1, 5, 10 },
            { 10, 5, 1, 1, 1, 1, 5, 10 },
            { -60, -80, 5, 5, 5, 5, -80, -60 },
            { 90, -60, 10, 10, 10, 10, -60, 90 }
    }; // init a matrix of points in the map
       // public boolean AIGO = false;

    public EasyAI(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public int[] AIStep() {// the easy level ai search for maximum can flip right now
        int max_num = 0;
        int[] step_cordinate = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameRule.getExpectMatrix()[i][j] != 0 && GameRule.getExpectMatrix()[i][j] > max_num) {
                    step_cordinate[0] = i;
                    step_cordinate[1] = j;
                    max_num = GameRule.getExpectMatrix()[i][j];
                }
            }
        }
        return step_cordinate;
    }

    public int[] middle_AIStep() {// middle level ai
        int[] step_cordinate = new int[2];
        int ME = 0; // AI score
        // int maxx = 0;
        int[][] expectnow = new int[8][8];
        int[][] weightMap = new int[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (GameRule.getExpectMatrix()[i][j] != 0) {
                    ME = mapPoint[i][j] + GameRule.getExpectMatrix()[i][j];
                    int[][] newMap = ChessBoard.instance();
                    newMap[i][j] = getChessPiece().getType();
                    if ((i == 0 && j == 0) || (i == 0 && j == 8 - 1) || (i == 8 - 1 && j == 8 - 1)
                            || (i == 8 - 1 && j == 0)) {
                        step_cordinate[0] = i;
                        step_cordinate[1] = j;
                        return step_cordinate; // if the step can in the corner,then return
                    }
                    newMap = GameRule.virtualFlip(i, j, getChessPiece().getType(), newMap);// simulate this step

                    int YOU = -1050; // 探知对手行动力与局势
                    for (int k = 0; k < 8; ++k)
                        for (int l = 0; l < 8; ++l) {
                            expectnow[k][l] = GameRule.virtual_numCanFlip(k, l, -1 * getChessPiece().getType(), newMap); // calculate
                                                                                                                         // the
                                                                                                                         // oppoment
                                                                                                                         // score
                            if (expectnow[k][l] != 0) {
                                YOU = YOU < mapPoint[k][l] + expectnow[k][l] ? mapPoint[k][l] + expectnow[k][l] : YOU;
                            }
                        }
                    weightMap[i][j] = ME - YOU;// calculate the map weight from ai score -opponent score
                }
            }
        }
        step_cordinate = findMaxWeight(weightMap);
        int[][] newWeightMap = weightMap;
        // for (int a = 0; a < 8; a++) {
        // for (int b = 0; b < 8; b++) {
        // System.out.print(newWeightMap[a][b] + " ");
        // }
        // System.out.println();
        // }
        // while(step_cordinate!=null&&step_cordinate[0]!=-1&&((step_cordinate[0]<2&&step_cordinate[1]<2)||(step_cordinate[0]<2&&7-step_cordinate[1]<2)||(7-step_cordinate[0]<2&&step_cordinate[1]<2)||(7-step_cordinate[0]<2&&7-step_cordinate[1]<2))){
        // newWeightMap[step_cordinate[0]][step_cordinate[1]]=0;
        // step_cordinate = findMaxWeight(newWeightMap);
        // }
        // if (step_cordinate[0] != -1)
        // return step_cordinate;
        // else
        return findMaxWeight(weightMap);
    }

    private int[] findMaxWeight(int[][] weightMap) {// reture the step corresponding to the biggest weight in the map
        int max = -9999999;
        int[] cords = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (weightMap[i][j] != 0) {
                    if (weightMap[i][j] > max) {
                        max = weightMap[i][j];
                        cords[0] = i;
                        cords[1] = j;
                    }
                }
            }
        }
        // if(cords[0] ==0&&cords[1] ==0){
        // cords[0] = -1;
        // }
        return cords;
    }

    public int[] diff_AIStep() {// the difficult level if ai
        int[] step_cordinate = new int[2];
        // int maxx = -10005;
        int[][] expectnow = GameRule.getExpectMatrix();
        int[][] mapp = ChessBoard.instance();
        int[][] weightMap = new int[8][8];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (expectnow[i][j] != 0) {
                    if ((i == 0 && j == 0) || (i == 0 && j == 8 - 1) || (i == 8 - 1 && j == 8 - 1)
                            || (i == 8 - 1 && j == 0)) {
                        step_cordinate[0] = i;
                        step_cordinate[1] = j;
                        return step_cordinate; // return if in corner
                    }
                    int k = max_point(i, j, mapp, expectnow, 0, 2); // deepin for searching
                    // if (k >= maxx)
                    // {
                    // maxx = k;
                    // weightMap[i][j] = maxx;
                    // }
                    weightMap[i][j] = k;
                }
            }

        step_cordinate = findMaxWeight(weightMap);// find the max weight
        int[][] newWeightMap = weightMap;

        // for (int a = 0; a < 8; a++) {
        // for (int b = 0; b < 8; b++) {
        // System.out.print(newWeightMap[a][b] + " ");
        // }
        // System.out.println();
        // }

        return step_cordinate;
    }

    public int max_point(int x, int y, int[][] mapnow, int[][] expectnow, int depin, int depinmax) {// max score in the
                                                                                                    // map
        if (depin >= depinmax)
            return 0;// the exit of the recursion

        int maxx = -10005;

        int[][] expectnow2 = new int[8][8];
        int[][] mapnow2 = new int[8][8];
        int[][] mapnext = new int[8][8];
        int[][] expectlast = new int[8][8];

        copymap(mapnow2, mapnow);
        mapnow2[x][y] = getChessPiece().getType();
        int ME = mapPoint[x][y] + expectnow[x][y];
        mapnow2 = GameRule.virtualFlip(x, y, getChessPiece().getType(), mapnow2);// perfoem the ai step
        int MAXEXPECT = 0, LINEEXPECT = 0;

        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j) {
                // expectnow[k][l] = GameRule.virtual_numCanFlip(k,
                // l,-1*getChessPiece().getType(), newMap);
                expectnow2[i][j] = GameRule.virtual_numCanFlip(i, j, -1 * getChessPiece().getType(), mapnow2); // get
                                                                                                               // the
                                                                                                               // opponent
                                                                                                               // expect
                                                                                                               // matrix
                if (expectnow2[i][j] != 0) {
                    ++MAXEXPECT;
                    if ((i == 0 && j == 0) || (i == 0 && j == 8 - 1) || (i == 8 - 1 && j == 8 - 1)
                            || (i == 8 - 1 && j == 0))
                        return -1800; // return a large minus score if the opponent can draw in the corner
                    if ((i < 2 && j < 2) || (i < 2 && 8 - j - 1 < 2) || (8 - 1 - i < 2 && j < 2)
                            || (8 - 1 - i < 2 && 8 - 1 - j < 2))
                        ++LINEEXPECT;
                }
            }
        if (LINEEXPECT * 10 > MAXEXPECT * 7)
            return 1800; // cut if the opponent situation is bad

        for (int i = 0; i < 8; i++)// the following is similiar to the middle ai
            for (int j = 0; j < 8; j++)
                if (expectnow2[i][j] != 0) {
                    int YOU = mapPoint[i][j] + expectnow2[i][j];
                    copymap(mapnext, mapnow2);
                    mapnext[i][j] = -1 * getChessPiece().getType();
                    mapnext = GameRule.virtualFlip(i, j, -1 * getChessPiece().getType(), mapnext);
                    for (int k = 0; k < 8; k++)
                        for (int l = 0; l < 8; l++)
                            expectlast[k][l] = GameRule.virtual_numCanFlip(k, l, getChessPiece().getType(), mapnext);

                    for (int k = 0; k < 8; k++)
                        for (int l = 0; l < 8; l++)
                            if (expectlast[k][l] != 0) {
                                int nowm = ME - YOU + max_point(k, l, mapnext, expectlast, depin + 1, depinmax);
                                maxx = maxx < nowm ? nowm : maxx;
                            }
                }

        return maxx;
    }

    public void copymap(int[][] one, int[][] last) // 拷贝地图
    {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                one[i][j] = last[i][j];
    }

    @Override
    public void run() { // a new thread to perform the ai step autamatically when player has done
        // TODO Auto-generated method stub
        while (true) {
            System.out.print("");
            if (GameFrame.AIGO) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // System.out.println(GameFrame.AIGO);
                GameFrame.controller.AIGame();
                GameFrame.AIGO = false;
            }
        }
    }
}
