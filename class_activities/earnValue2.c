#include <stdio.h>

float calculaValorGanho() {

  float vv, vc, qd, ganho;

  scanf("%f %f %f", &vv, &vc, &qd);

  ganho = vv - (vc * qd);

  return ganho;
}

int main(void) {

  printf("Valor ganho: R$ %.2f \n", calculaValorGanho());

  return 0;
}
