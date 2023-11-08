#include <stdio.h>

struct product
{
	char sig;
	float price;
} p1, p2;

int main(void)
{
	int i;
	float totalPrice = 0;

	p1.sig = 'A';
	p1.price = 54.55;

	p2.sig = 'B';
	p2.price = 109.55;

	printf("Produto %c: R$ %.2f\n", p1.sig, p1.price);

	printf("Produto %c: R$ %.2f\n", p2.sig, p2.price);

	totalPrice = p1.price + p2.price;

	printf("Preço total: %.2f\n", totalPrice);

	return 0;
}
