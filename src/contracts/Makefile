.PHONY: all test clean

compile-sol:
	solc sol/*.sol --bin --abi --overwrite --optimize -o compiled/

generate-java:
	web3j solidity generate --binFile=compiled/WalletERC20.bin --abiFile=compiled/WalletERC20.abi -o .. -p contracts.generated
	web3j solidity generate --binFile=compiled/TokenERC20.bin --abiFile=compiled/TokenERC20.abi -o .. -p contracts.generated
	web3j solidity generate --binFile=compiled/MyContract.bin --abiFile=compiled/MyContract.abi -o .. -p contracts.generated
	web3j solidity generate --binFile=compiled/Crowdsale.bin --abiFile=compiled/Crowdsale.abi -o .. -p contracts.generated

image:
	make compile-sol
	make generate-java

