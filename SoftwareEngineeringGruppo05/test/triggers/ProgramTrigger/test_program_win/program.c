#include <stdio.h>

int main(int argc, char *argv[]) {
    // Verifica se sono presenti parametri sulla riga di comando
    if (argc < 2) {
        exit(1);
    }

    // Stampa la stringa "ciao"
    printf("Ciao!\n");

    return 0;
}