pragma solidity ^0.5.2;

contract MyContract {
    uint myVariable;
    address owner;
    
    mapping(address => Permission) myAddressMapping;
    
    event NumberIsIncreased(address indexed whoIncreased, uint256 indexed oldNumber, uint256 indexed newNumber);

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
    
    constructor() public payable {
        myVariable = 5;
        owner = msg.sender;
        
        myAddressMapping[msg.sender] = Permission(true, 5);
    }
    
    function setMyVariable(uint myNewVariable) public onlyowner {
        emit NumberIsIncreased(msg.sender, myVariable, myNewVariable);
        myVariable = myNewVariable;
    }
    
    function getMyVariable() view public returns (uint) {
        return myVariable;
    }
    
    function getMyContractBalance() view public returns (uint) {
        return address(this).balance;
    }
    
    function () payable external {
        
    }
}

