#include <stdio.h>

int main() {
  int a = 7, b = 15, c = 22;

  for (a = c; b < a; a -= 3) {
    // o loop for só vai executar o comando dentro da terceira parte do
    // parâmetro (a -= 3) na segunda vez que ele rodar
    c = a - 2 * b;

    printf("%d %d %d\n", a, b, c);
  }

  // no primeiro loop, b ainda é maior que a (b = 15, a = 7), então o comando "a
  // -= 3" não é executado, sendo executado somente a partir do segundo loop,
  // onde a já vai ser igual a 22, sendo maior que b.

  return 0;
}
