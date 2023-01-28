#include <stdio.h>

float calculaValorGanho(float vv, float vc, float qd) {

  float ganho;

  ganho = vv - (vc * qd);

  printf("Valor ganho: %f \n", ganho);

  return 0;
}

int main() {
  float vv, vc, qd;

  scanf("%f %f %f", &vv, &vc, &qd);

  calculaValorGanho(vv, vc, qd);

  return 0;
}
