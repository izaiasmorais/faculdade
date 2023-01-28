#include <stdio.h>

int main() {

  int age;
  float weight;
  float height;

  printf("Digite sua idade:\n");
  scanf("%d", &age);

  if (age >= 18 && age <= 30) {
    printf("Digite seu peso e altura:\n");
    scanf("%f %f", &weight, &height);

    if (weight > 100 || height > 2) {
      printf("Você foi aceito!\n");
    } else {
      printf("Nenhum dos requisitos cumpridos\n");
    }
  } else {
    printf("Sua idade deve estar entre 18 e 30");
  }

  return 0;
}
