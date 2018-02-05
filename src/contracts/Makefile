.PHONY: all test clean

compile-sol:
	solc sol/*.sol --bin --abi --overwrite --optimize -o compiled/

generate-java:
	web3j solidity generate compiled/*.bin compiled/*.abi -o ../.. -p ethereum.contracts.generated

image:
	make compile-sol
	make generate-java

