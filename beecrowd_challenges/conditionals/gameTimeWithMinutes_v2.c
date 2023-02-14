#include <stdio.h>

int main() {

  int hi, mi, hf, mf;

  int dif;

  scanf("%d %d %d %d", &hi, &mi, &hf, &mf);

  dif = ((hf * 60) + mf) - ((hi * 60) + mi);

  if (dif <= 0) {
    dif += 24 * 60;
  }

  printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S)\n", dif / 60, dif % 60);

  return 0;
}
