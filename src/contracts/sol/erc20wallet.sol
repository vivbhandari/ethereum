pragma solidity ^0.5.2;

contract WalletERC20 {
    address owner;
    mapping (string => uint256) tokenBalance;
        
    modifier onlyowner () {
        if(msg.sender == owner) {
            _;
        } else {
            revert();
        }
    }
    
    constructor() public payable {
        owner = msg.sender;
    }
        
    function getTokenBalance(string memory _tokenName) view public returns (uint256) {
        return tokenBalance[_tokenName];
    }
    

    function setTokenBalance(string memory _tokenName, uint256 _value) public onlyowner returns (bool success) {
        tokenBalance[_tokenName] = _value;
        return true;
    }

    function getEtherBalance() view public returns (uint) {
        return address(this).balance;
    }

    function transferEthers(address payable _receiver, uint256 _value, uint256 _gas) public onlyowner returns (bool success) {
        _receiver.call.value(_value).gas(_gas)("");
        return true;
    }
    
    function () payable external {   
    }
}

