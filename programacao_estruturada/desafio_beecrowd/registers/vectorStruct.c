#include <stdio.h>
#define tam 2

struct product
{
	char sig[2];
	int price;
};

int main()
{
	int i;
	int precoTotal = 0;
	struct product p[tam];

	for (i = 0; i < tam; i++)
	{
		scanf("%s", p[i].sig);
		scanf("%d", &p[i].price);
	}

	for (i = 0; i < tam; i++)
	{
		printf("Produto %s: ", p[i].sig);
		printf("R$ %d\n", p[i].price);

		precoTotal += p[i].price;
	}

	printf("Preço total: R$ %d\n", precoTotal);

	return 0;
}
