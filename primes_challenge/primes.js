const main = () => {
  const primeNumbers = [];

  for (var counter = 0; counter <= 1000; counter++) {
    var notPrime = false;

    for (var i = 2; i <= counter; i++) {
      if (counter % i === 0 && i !== counter) {
        notPrime = true;
      }
    }
    if (notPrime === false) {
      primeNumbers.push(counter);
    }
  }

  const sum = primeNumbers.reduce((partialSum, a) => partialSum + a, 0);

  return console.log(sum);
};

main();
