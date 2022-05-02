import com.diogonunes.jcdp.color.api.Ansi;

class Settings {

    int size;
    int wallsCount;
    int enemiesCount;
    int player_x;
    int player_y;
    int exit_x;
    int exit_y;
    boolean way_to_exit;

    Ansi.BColor enemyColor;
    Ansi.BColor playerColor;
    Ansi.BColor wallColor;
    Ansi.BColor goalColor;
    Ansi.BColor emptyColor;

    Settings () {
        this.size = 0;
        this.wallsCount = 0;
        this.enemiesCount = 0;
        this.player_x = 0;
        this.player_y = 0;
        this.exit_x = 0;
        this.exit_y = 0;
        this.way_to_exit = false;
    }
}
