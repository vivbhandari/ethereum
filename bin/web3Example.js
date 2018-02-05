/**
 * 
 */
print('Hello World!!')

load("/Users/vivb/eclipse/workspace/web3.js/dist/web3.min.js")
var Web3 = require('web3');
if (typeof web3 !== 'undefined') {
	web3 = new Web3(web3.currentProvider);
} else {
	// set the provider you want from Web3.providers
	web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:8545"));
}

function calc(x, y) {
	return x + y
}

//function getBalance() {
//	return web3.eth.getBalance(web3.eth.accounts[0])
//}
