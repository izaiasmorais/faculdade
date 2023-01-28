#include <stdio.h>

int main() {
  int x;

  char ar[][12] = {"January",   "February", "March",    "April",
                   "May",       "June",     "July",     "August",
                   "September", "October",  "November", "December"};

  printf("Escolha um mês pelo número [1-12]: \n");

  scanf("%d", &x);

  printf("O mês escolhido foi: %s \n", ar[x - 1]);

  return 0;
}
