import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class Program {

    public static char enemiesSymbol;
    public static char emptySymbol;
    public static char wallSymbol;
    public static char playerSymbol;
    public static char exitSymbol;


    public static void main(String[] args) {

        Settings settings = new Settings();
        int x = 0;
        int y = 0;

        if (args.length != 4 || !(args[0].length() > 1) || !(args[1].length() > 1) || !(args[2].length() > 1) || !(args[3].length() > 1)) {
            System.err.println("Error: wrong number of arguments");
            System.exit(-1);
        } else {
            String enemiesCount[] = args[0].split("=");
            String wallsCount[] = args[1].split("=");
            String sizeWindow[] = args[2].split("=");
            String profile[] = args[3].split("=");

            if ((enemiesCount.length != 2) || (!enemiesCount[0].equals("--enemiesCount"))) {
                System.err.println("Error: wrong argument");
                System.exit(-1);
            } else {
                settings.enemiesCount = Integer.parseInt(enemiesCount[1]);
            }
            if ((wallsCount.length != 2) || (!wallsCount[0].equals("--wallsCount"))) {
                System.err.println("Error: wrong argument");
                System.exit(-1);
            } else {
                settings.wallsCount = Integer.parseInt(wallsCount[1]);
            }
            if ((sizeWindow.length != 2) || (!sizeWindow[0].equals("--size"))) {
                System.err.println("Error: wrong argument");
                System.exit(-1);
            } else {
                settings.size = Integer.parseInt(sizeWindow[1]);
            }
        }


        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load("application-production.properties");
            enemiesSymbol = (config.getString("enemy.char").charAt(0));
            playerSymbol = (config.getString("player.char")).charAt(0);
            wallSymbol = config.getString("wall.char").charAt(0);
            exitSymbol = config.getString("goal.char").charAt(0);
            emptySymbol = config.getString("empty.char").charAt(0);


            settings.enemyColor = Ansi.BColor.valueOf(config.getString("enemy.color"));
            settings.playerColor = Ansi.BColor.valueOf(config.getString("player.color"));
            settings.wallColor = Ansi.BColor.valueOf(config.getString("wall.color"));
            settings.goalColor = Ansi.BColor.valueOf(config.getString("goal.color"));
            settings.emptyColor = Ansi.BColor.valueOf(config.getString("empty.color"));

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }


        char[][] map = new char[settings.size][settings.size];

        if (settings.size * settings.size < settings.enemiesCount + settings.wallsCount + 2) {
            System.out.println("Wrong settings");
            System.exit(-1);
        }


        while (!settings.way_to_exit) {
            create_map(map, settings);
            check_map(map, settings);
        }
//        print_map(map, settings);

        try {
            drawField(map, settings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        moviePlayer(map, settings);
    }

    static void check_map(char map[][], Settings settings) {

        int way = 1;
        map[settings.player_x][settings.player_y] = '?';

        while (way == 1) {
            way = 0;
            for (int i = 0; i < settings.size; i++) {
                for (int j = 0; j < settings.size; j++) {
                    if ((map[i][j] == '?' || map[i][j] == '}') && j + 1 < settings.size && (map[i][j + 1] == emptySymbol || map[i][j + 1] == enemiesSymbol)) {
                        if (map[i][j + 1] == emptySymbol)
                            map[i][j + 1] = '?';
                        else
                            map[i][j + 1] = '}';
                        way = 1;
                    }
                    if ((map[i][j] == '?' || map[i][j] == '}') && i + 1 < settings.size && (map[i + 1][j] == emptySymbol || map[i + 1][j] == enemiesSymbol)) {
                        if (map[i + 1][j] == emptySymbol)
                            map[i + 1][j] = '?';
                        else
                            map[i + 1][j] = '}';
                        way = 1;
                    }
                    if ((map[i][j] == '?' || map[i][j] == '}') && j - 1 >= 0 && (map[i][j - 1] == emptySymbol || map[i][j - 1] == enemiesSymbol)) {
                        if (map[i][j - 1] == emptySymbol)
                            map[i][j - 1] = '?';
                        else
                            map[i][j - 1] = '}';
                        way = 1;
                    }
                    if ((map[i][j] == '?' || map[i][j] == '}') && i - 1 >= 0 && (map[i - 1][j] == emptySymbol || map[i - 1][j] == enemiesSymbol)) {
                        if (map[i - 1][j] == emptySymbol)
                            map[i - 1][j] = '?';
                        else
                            map[i - 1][j] = '}';
                        way = 1;
                    }
                }
            }
        }

        if (settings.exit_x + 1 < settings.size && (map[settings.exit_x + 1][settings.exit_y] == '?' || map[settings.exit_x + 1][settings.exit_y] == '}'))
            settings.way_to_exit = true;
        if (settings.exit_y + 1 < settings.size && (map[settings.exit_x][settings.exit_y + 1] == '?' || map[settings.exit_x][settings.exit_y + 1] == '}'))
            settings.way_to_exit = true;
        if (settings.exit_x - 1 >= 0 && (map[settings.exit_x - 1][settings.exit_y] == '?' || map[settings.exit_x - 1][settings.exit_y] == '}'))
            settings.way_to_exit = true;
        if (settings.exit_y - 1 >= 0 && (map[settings.exit_x][settings.exit_y - 1] == '?' || map[settings.exit_x][settings.exit_y - 1] == '}'))
            settings.way_to_exit = true;


        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {
                if (map[i][j] == '?')
                    map[i][j] = emptySymbol;
                if (map[i][j] == '}')
                    map[i][j] = enemiesSymbol;
            }
        }
        map[settings.player_x][settings.player_y] = playerSymbol;
//        System.out.println(settings.way_to_exit);
    }

    static void create_map(char map[][], Settings settings) {
        int x;
        int y;

        int wallsCreated = 0;
        int enemiesCreated = 0;
        int playerCreated = 0;
        int exitCreated = 0;

        Random random = new Random();

        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {
                map[i][j] = emptySymbol;
            }
        }

        while (enemiesCreated < settings.enemiesCount) {
            x = random.nextInt(settings.size);
            y = random.nextInt(settings.size);
            if (map[x][y] == emptySymbol) {
                map[x][y] = enemiesSymbol;
                enemiesCreated++;
            }
        }

        while (wallsCreated < settings.wallsCount) {
            x = random.nextInt(settings.size);
            y = random.nextInt(settings.size);
            if (map[x][y] == emptySymbol) {
                map[x][y] = wallSymbol;
                wallsCreated++;
            }
        }

        while (playerCreated == 0) {
            x = random.nextInt(settings.size);
            y = random.nextInt(settings.size);
            if (map[x][y] == emptySymbol) {
                map[x][y] = playerSymbol;
                settings.player_x = x;
                settings.player_y = y;
                playerCreated++;
            }
        }

        while (exitCreated == 0) {
            x = random.nextInt(settings.size);
            y = random.nextInt(settings.size);
            if (map[x][y] == emptySymbol) {
                map[x][y] = exitSymbol;
                settings.exit_x = x;
                settings.exit_y = y;
                exitCreated++;
            }
        }
    }

    public static void drawField(char map[][], Settings settings) throws IOException {

        ColoredPrinter coloredPrinter = new ColoredPrinter();

        coloredPrinter.setForegroundColor(Ansi.FColor.valueOf("BLACK"));
        Runtime.getRuntime().exec("clear");
        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {
                //switch (map[i][j]) {
                if (map[i][j] == enemiesSymbol) {
                    coloredPrinter.setBackgroundColor(settings.enemyColor);
                    coloredPrinter.print(enemiesSymbol);
                    //break;
                } else if (map[i][j] == playerSymbol) {
                    coloredPrinter.setBackgroundColor(settings.playerColor);
                    coloredPrinter.print(playerSymbol);
                    // break;
                } else if (map[i][j] == wallSymbol) {
                    coloredPrinter.setBackgroundColor(settings.wallColor);
                    coloredPrinter.print(wallSymbol);
                    // break;
                } else if (map[i][j] == exitSymbol) {
                    coloredPrinter.setBackgroundColor(settings.goalColor);
                    coloredPrinter.print(exitSymbol);
                    // break;
                } else {
                    coloredPrinter.setBackgroundColor(settings.emptyColor);
                    coloredPrinter.print(emptySymbol);
                    // break;
                }
            }
            System.out.println();
        }
    }


    static void print_map(char map[][], Settings settings) {
        ColoredPrinter coloredPrinter = new ColoredPrinter();
        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {
                if (map[i][j] == emptySymbol)
                    coloredPrinter.print(emptySymbol, Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.YELLOW);
                else if (map[i][j] == wallSymbol)
                    coloredPrinter.print(wallSymbol, Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.MAGENTA);
                else if (map[i][j] == enemiesSymbol)
                    coloredPrinter.print(enemiesSymbol, Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.RED);
                else if (map[i][j] == playerSymbol)
                    coloredPrinter.print(playerSymbol, Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.GREEN);
                else
                    coloredPrinter.print(exitSymbol, Ansi.Attribute.BOLD, Ansi.FColor.BLACK, Ansi.BColor.BLUE);
            }
            System.out.println();
        }
    }

    public static void moviePlayer(char map[][], Settings settings) {
        Scanner scanner = new Scanner(System.in);
        char key = '6';
        while (true) {
            if (map[settings.player_x][settings.player_y] == enemiesSymbol) {
                System.out.println("Game over");
                System.exit(0);
            }

            try {
                key = scanner.nextLine().charAt(0);

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Empty line");
            }
            if (key == 'a' || key == 'A') {
                if (settings.player_y - 1 >= 0 && map[settings.player_x][settings.player_y - 1] == enemiesSymbol) {
                    System.out.println("Game over");
                    System.exit(0);
                } else if (settings.player_y - 1 >= 0 && map[settings.player_x][settings.player_y - 1] == exitSymbol) {
                    System.out.println("You win!!! Game over");
                    System.exit(0);
                } else if (settings.player_y - 1 >= 0 && map[settings.player_x][settings.player_y - 1] != wallSymbol) {
                    map[settings.player_x][settings.player_y] = emptySymbol;
                    map[settings.player_x][settings.player_y - 1] = playerSymbol;
                    settings.player_y -= 1;
                }
            } else if (key == 'd' || key == 'D') {
                if (settings.player_y + 1 < settings.size && map[settings.player_x][settings.player_y + 1] == enemiesSymbol) {
                    System.out.println("Game over");
                    System.exit(0);
                } else if (settings.player_y + 1 < settings.size && map[settings.player_x][settings.player_y + 1] == exitSymbol) {
                    System.out.println("You win!!! Game over");
                    System.exit(0);
                } else if (settings.player_y + 1 < settings.size && map[settings.player_x][settings.player_y + 1] != wallSymbol) {
                    map[settings.player_x][settings.player_y] = emptySymbol;
                    map[settings.player_x][settings.player_y + 1] = playerSymbol;
                    settings.player_y += 1;
                }
            } else if (key == 'w' || key == 'W') {
                if (settings.player_x - 1 >= 0 && map[settings.player_x - 1][settings.player_y] == enemiesSymbol) {
                    System.out.println("Game over");
                    System.exit(0);
                } else if (settings.player_x - 1 >= 0 && map[settings.player_x - 1][settings.player_y] == exitSymbol) {
                    System.out.println("You win!!! Game over");
                    System.exit(0);
                } else if (settings.player_x - 1 >= 0 && map[settings.player_x - 1][settings.player_y] != wallSymbol) {
                    map[settings.player_x][settings.player_y] = emptySymbol;
                    map[settings.player_x - 1][settings.player_y] = playerSymbol;
                    settings.player_x -= 1;
                }
            } else if (key == 's' || key == 'S') {
                if (settings.player_x + 1 < settings.size && map[settings.player_x + 1][settings.player_y] == enemiesSymbol) {
                    System.out.println("Game over");
                    System.exit(0);
                } else if (settings.player_x + 1 < settings.size && map[settings.player_x + 1][settings.player_y] == exitSymbol) {
                    System.out.println("You win!!! Game over");
                    System.exit(0);
                } else if (settings.player_x + 1 < settings.size && map[settings.player_x + 1][settings.player_y] != wallSymbol) {
                    map[settings.player_x][settings.player_y] = emptySymbol;
                    map[settings.player_x + 1][settings.player_y] = playerSymbol;
                    settings.player_x += 1;
                }
            } else if (key == '9') {
                System.out.println("Game over");
                System.exit(0);
            }
            try {
                drawField(map, settings);
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    key = scanner.nextLine().charAt(0);

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Empty line");
                }
                if (key == '8')
                    break;
            }

            for (int i = 0; i < settings.size; i++) {
                for (int j = 0; j < settings.size; j++) {
                    if (map[i][j] == enemiesSymbol) {
                        if (i < settings.player_x && i + 1 < settings.size && map[i + 1][j] != wallSymbol && map[i + 1][j] != exitSymbol && map[i + 1][j] != enemiesSymbol && map[i + 1][j] != '?') {
                            map[i][j] = emptySymbol;
                            map[i + 1][j] = '?';
                        } else if (i > settings.player_x && i - 1 > 0 && map[i - 1][j] != wallSymbol && map[i - 1][j] != exitSymbol && map[i - 1][j] != enemiesSymbol && map[i - 1][j] != '?') {
                            map[i][j] = emptySymbol;
                            map[i - 1][j] = '?';
                        } else if (j < settings.player_y && j + 1 < settings.size && map[i][j + 1] != wallSymbol && map[i][j + 1] != exitSymbol && map[i][j + 1] != enemiesSymbol && map[i][j + 1] != '?') {
                            map[i][j] = emptySymbol;
                            map[i][j + 1] = '?';
                        } else if (j > settings.player_y && j - 1 > 0 && map[i][j - 1] != wallSymbol && map[i][j - 1] != exitSymbol && map[i][j - 1] != enemiesSymbol && map[i][j - 1] != '?') {
                            map[i][j] = emptySymbol;
                            map[i][j - 1] = '?';
                        }
                    }

                }
            }

            for (int i = 0; i < settings.size; i++) {
                for (int j = 0; j < settings.size; j++) {
                    if (map[i][j] == '?')
                        map[i][j] = enemiesSymbol;
                }
            }

            try {
                drawField(map, settings);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void movieEnemy(char map[][], Settings settings) {
//
//    }

}