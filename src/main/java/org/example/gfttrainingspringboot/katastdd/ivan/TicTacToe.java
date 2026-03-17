package org.tictactoe;

public class TicTacToe {

    private char[][] tablero = new char[3][3];
    private char turnoActual = 'X';
    private char ganador = ' ';
    private boolean juegoTerminado = false;
    private int movimientosRealizados = 0;

    public TicTacToe() {
        for (int fila = 0; fila < 3; fila++)
            for (int col = 0; col < 3; col++)
                tablero[fila][col] = ' ';
    }

    /**
     * Registra el movimiento del jugador actual.
     * fila  fila del tablero (0-2)
     * col   columna del tablero (0-2)
     */
    public void jugar(int fila, int col) {
        if (juegoTerminado)
            throw new IllegalStateException("El juego ya ha terminado.");

        if (tablero[fila][col] != ' ')
            throw new IllegalArgumentException(
                    "Posición (" + fila + "," + col + ") ya está ocupada.");

        tablero[fila][col] = turnoActual;
        movimientosRealizados++;

        if (hayGanador(turnoActual)) {
            ganador = turnoActual;
            juegoTerminado = true;
        } else if (movimientosRealizados == 9) {
            juegoTerminado = true; // empate
        } else {
            turnoActual = (turnoActual == 'X') ? 'O' : 'X';
        }
    }

    private boolean hayGanador(char jugador) {
        // Comprobar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador)
                return true;
            if (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador)
                return true;
        }
        // Comprobar diagonales
        if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador)
            return true;
        if (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador)
            return true;
        return false;
    }

    public char getGanador()          { return ganador; }
    public boolean isEmpate()         { return juegoTerminado && ganador == ' '; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
    public char getTurnoActual()      { return turnoActual; }
}