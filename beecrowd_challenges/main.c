#include <stdio.h>

int main() {
  int hi, mi, hf, mf, ht, mt;

  scanf("%d %d %d %d", &hi, &mi, &hf, &mf);

  if (hi == hf) {
    ht = 24;

  } else if (hi > hf) {
    ht = 24 - hi + hf;

  } else if (hi < hf) {
    ht = hf - hi;
  }

  if (mi < mf) {
    mt = mf - mi;

  } else if (mi > mf) {
    mt = 60 - mi + mf;
    ht = ht - 1;

  } else if (mi == mf) {
    mt = 0;
  }

  printf("O JOGO DUROU %d HORA(S) E %d MINUTO(S) \n", ht, mt);

  return 0;
}
