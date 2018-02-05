pragma solidity ^0.4.0;

contract MyContract {
    uint myVariable;
    address owner;
    
    mapping(address => Permission) myAddressMapping;
    
    modifier onlyowner () {
        if(msg.sender == owner) {
            _;
        } else {
            revert();
        }
    }
    struct Permission {
        bool isAllowed;
        uint maxTransferAmount;
    }
    
    function MyContract() public payable {
        myVariable = 5;
        owner = msg.sender;
        
        myAddressMapping[msg.sender] = Permission(true, 5);
    }
    
    function setMyVariable(uint myNewVariable) public onlyowner {
        myVariable = myNewVariable;
    }
    
    function getMyVariable() constant public returns (uint) {
        return myVariable;
    }
    
    function getMyContractBalance() constant public returns (uint) {
        return this.balance;
    }
    
    function () payable public {
        
    }
}

