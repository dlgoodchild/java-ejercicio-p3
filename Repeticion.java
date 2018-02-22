package practica3;

import java.util.Scanner;

public class Repeticion {

  final static int TAMANO = 6;
  final static char JEDI = 'J';
  final static char VADER = 'V';
  final static char SIDIOUS = 'S';
  final static char MAUL = 'M';
  final static char VACIO = '_';

  static boolean armadura;

  public static void main(String[] args) {
    char[][] tablero = new char[TAMANO][TAMANO];
    Scanner teclado = new Scanner(System.in);
    char movimiento;
    char tipoEnemigo;

    armadura = true;

    inicializarTablero(tablero);

    do {
      mostrarTablero(tablero);
      movimiento = pedirMovimiento(teclado);

      jugadaJedi(tablero, movimiento);
      jugadaEnemigos(tablero);


      // realizar movimientos de enemigos

    } while (movimiento != 'f');

    mostrarTablero(tablero);
    teclado.close();
  }

  private static void jugadaEnemigos(char[][] tablero) {
    movimientoEnemigo(tablero, SIDIOUS);
    movimientoEnemigo(tablero, MAUL);
    movimientoEnemigo(tablero, VADER);
    //posicionValida
  }

  private static void movimientoEnemigo(char[][] tablero, char tipoEnemigo) {
    int posicionI = 0, posicionJ = 0, movimientoI = 0, movimientoJ = 0;

    //hacer metodo para poner vacio
    //hacer metodo de posiconvalida correctamente.
    //va el calculo del aleatorio en un bucle
    //llamar a las coordenadas inicializadas arriba porque se las tenemos que pasar a los metodos que llamemos


    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero.length; j++) {

        if (tablero[i][j] == tipoEnemigo) {
          //return i;
          tablero[i][j] = VACIO;
          posicionI = i;
          posicionJ = j;

        }
      }
    }

    do {
      if (posicionValida(posicionI, posicionJ)) {
        int aleatorio = (int) ((Math.random() * (4)));

        switch (aleatorio) {
          case 0:

            if (posicionI != 0) {
              tablero[posicionI - 1][posicionJ] = tipoEnemigo;
            }

            break;

          case 1:
            if (posicionJ != 5) {
              tablero[posicionI][posicionJ + 1] = tipoEnemigo;
            }
            break;

          case 2:
            if (posicionI != 5) {
              tablero[posicionI + 1][posicionJ] = tipoEnemigo;
            }

            break;
          case 3:

            if (posicionJ != 0) {
              tablero[posicionI][posicionJ - 1] = tipoEnemigo;
            }
        }
      }
    }
    while (!hayEnemigo(tablero, new char[]{tipoEnemigo}, posicionI, posicionJ));
  }

  private static boolean hayEnemigo(char[][] tablero, char[] tipoEnemigos, int x, int y) {
    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero.length; j++) {
        if ((new String(tipoEnemigos).indexOf(tablero[i][j])) >= 0) {
          x = i;
          y = j;
          return true;
        }
      }
    }

    return false;
  }

  private static char pedirMovimiento(Scanner teclado) {
    boolean validMovement;
    char movimiento;

    do {
      System.out.println("Introduce el movimiento del Jedi (a, w, d, s)");
      movimiento = teclado.nextLine().toLowerCase().charAt(0);
      validMovement = (movimiento == 'a' || movimiento == 'w' || movimiento == 'd' || movimiento == 's');
    }
    while (!validMovement);

    return movimiento;
  }

  private static void mostrarTablero(char[][] tablero) {
    for (int i = 0; i < tablero.length + 2; i++) {
      System.out.print("0 ");
    }
    System.out.println();

    for (int i = 0; i < tablero.length; i++) {
      System.out.print("0 ");
      for (int j = 0; j < tablero[i].length; j++) {
        System.out.print(tablero[i][j] + " ");
      }
      System.out.println("0");
    }

    for (int i = 0; i < tablero.length + 2; i++) {
      System.out.print("0 ");
    }
    System.out.println();
  }

  private static boolean posicionValida(int posicionI, int posicionJ) {
    return (posicionI < 6 && posicionI >= 0 && posicionJ >= 0 && posicionJ < 6);
  }

  private static void emplazarJugadores(char[][] tablero) {
    tablero[0][0] = JEDI;
    tablero[0][TAMANO - 1] = SIDIOUS;
    tablero[TAMANO - 1][0] = VADER;
    tablero[TAMANO - 1][TAMANO - 1] = MAUL;
  }

  private static void inicializarTablero(char[][] tablero) {
    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero[i].length; j++) {
        tablero[i][j] = VACIO;
      }
    }
    // la emplazamiento de jugadores es parte de la iniciación
    emplazarJugadores(tablero);
  }

  private static void jugadaJedi(char[][] tablero, char movimiento) {
    int x = 0, y = 0;

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero.length; j++) {
        if ((tablero[i][j] == JEDI)) {

          tablero[i][j] = VACIO;

          x = i;
          y = j;
        }
      }
    }

    switch (movimiento) {

      case 'a':

        if (posicionValida(x, y) && y != 0) {
          y--;
          tablero[x][y] = JEDI;
          if (hayEnemigo(tablero, new char[]{MAUL, VADER, SIDIOUS}, x, y)) {

            tablero[x][y] = VACIO;
          }
        } else {
          tablero[x][y] = JEDI;
        }
        break;

      case 's':

        if (posicionValida(x, y) && x != 5) {
          x++;
          tablero[x][y] = JEDI;
          if (hayEnemigo(tablero, new char[]{MAUL, VADER, SIDIOUS}, x, y)) {
            tablero[x][y] = JEDI;
          }
        } else {
          tablero[x][y] = JEDI;
        }

        break;

      case 'd':

        if (posicionValida(x, y) && y != 5) {
          y++;
          tablero[x][y] = JEDI;
          if (hayEnemigo(tablero, new char[]{MAUL, VADER, SIDIOUS}, x, y)) {
            tablero[x][y] = JEDI;
          }
        } else {
          tablero[x][y] = JEDI;
        }

        break;

      case 'w':

        if (posicionValida(x, y) && x != 0) {
          x--;
          tablero[x][y] = JEDI;
          if (hayEnemigo(tablero, new char[]{MAUL, VADER, SIDIOUS}, x, y)) {
            tablero[x][y] = JEDI;
          }
        } else {
          tablero[x][y] = JEDI;
        }

        break;
      default:
        System.out.println("no he introducido la tecla correcta");
    }
  }
}