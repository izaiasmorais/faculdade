#include <stdio.h>

float calculaValorGanho(float vv, float vc, float qd) {

  float ganho;

  ganho = vv - (vc * qd);

  return ganho;
}

int main(void) {

  float vv, vc, qd, ganho;

  scanf("%f %f %f", &vv, &vc, &qd);

  ganho = calculaValorGanho(vv, vc, qd);

  printf("Valor ganho: R$ %.2f \n", ganho);

  return 0;
}
