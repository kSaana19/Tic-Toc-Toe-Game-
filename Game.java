package snakegame;

import java.util.HashSet;
import java.util.Scanner;

public class Game {

    static HashSet<Integer> Al = new HashSet<>();
    static HashSet<Integer> com_set = new HashSet<>();

    public static void main(String args[]) {

        char[][] g_board = {
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '|', '-', '|', '-'},
                {' ', '|', ' ', '|', ' '},
        };

        print_board(g_board);
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your move (1-9): ");
            int user_set = in.nextInt();

            while (Al.contains(user_set) || com_set.contains(user_set) || user_set < 1 || user_set > 9) {
                System.out.println("Invalid move. Please try again.");
                System.out.print("Enter your move (1-9): ");
                user_set = in.nextInt();
            }

            p_holder(g_board, user_set, "you");

            String res = check_winner();
            if (!res.isEmpty()) {
                System.out.println(res);
                break;
            }

            int comp_set = Al();
            while (Al.contains(comp_set) || com_set.contains(comp_set)) {
                comp_set = Al();
            }

            p_holder(g_board, comp_set, "comp");

            res = check_winner();
            if (!res.isEmpty()) {
                System.out.println(res);
                break;
            }
        }
    }

    private static String check_winner() {
        HashSet<Integer> h1 = new HashSet<>(), h2 = new HashSet<>(), h3 = new HashSet<>(),
                h4 = new HashSet<>(), h5 = new HashSet<>(), h6 = new HashSet<>(),
                h7 = new HashSet<>(), h8 = new HashSet<>();

        h1.add(1); h1.add(2); h1.add(3);
        h2.add(4); h2.add(5); h2.add(6);
        h3.add(7); h3.add(8); h3.add(9);
        h4.add(1); h4.add(4); h4.add(7);
        h5.add(2); h5.add(5); h5.add(8);
        h6.add(3); h6.add(6); h6.add(9);
        h7.add(1); h7.add(5); h7.add(9);
        h8.add(3); h8.add(5); h8.add(7);

        HashSet<HashSet> set = new HashSet<>();
        set.add(h1); set.add(h2); set.add(h3);
        set.add(h4); set.add(h5); set.add(h6);
        set.add(h7); set.add(h8);

        for (HashSet c : set) {
            if (Al.containsAll(c))
                return "You won!";
            else if (com_set.containsAll(c))
                return "You lost!";
        }

        if (Al.size() + com_set.size() == 9)
            return "It's a draw!";
        return "";
    }

    private static int Al() {
        int max = 9;
        int min = 1;
        int range = max - min + 1;
        int res = (int) (Math.random() * range) + min;
        return res;
    }

    private static void p_holder(char[][] g_board, int pos, String user) {
        char symbol = user.equals("you") ? 'X' : 'O';
        if (user.equals("you"))
            Al.add(pos);
        else if (user.equals("comp"))
            com_set.add(pos);

        switch (pos) {
            case 1:
                g_board[0][0] = symbol;
                break;
            case 2:
                g_board[0][2] = symbol;
                break;
            case 3:
                g_board[0][4] = symbol;
                break;
            case 4:
                g_board[2][0] = symbol;
                break;
            case 5:
                g_board[2][2] = symbol;
                break;
            case 6:
                g_board[2][4] = symbol;
                break;
            case 7:
                g_board[4][0] = symbol;
                break;
            case 8:
                g_board[4][2] = symbol;
                break;
            case 9:
                g_board[4][4] = symbol;
                break;
            default:
                System.out.println("Invalid input");
        }
        print_board(g_board);
    }

    static void print_board(char[][] g_board) {
        for (int i = 0; i < g_board.length; i++) {
            for (int j = 0; j < g_board[0].length; j++) {
                System.out.print(g_board[i][j]);
            }
            System.out.println();
        }
    }
}
