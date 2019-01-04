package contracts.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class Crowdsale extends Contract {
    private static final String BINARY = "60806040526007805461ffff1916905534801561001b57600080fd5b5060405160a0806105ef833981018060405260a081101561003b57600080fd5b50805160208201516040830151606084015160809094015160008054600160a060020a03958616600160a060020a031991821617909155670de0b6b3a764000093840260015542603c909302929092016003559190930260045560058054929091169190921617905561053c806100b36000396000f3fe6080604052600436106100ae576000357c0100000000000000000000000000000000000000000000000000000000900480637a3a0e84116100765780637a3a0e84146102875780637b3e5e7b1461029c578063a035b1fe146102b1578063ea46193e146102c6578063fd6b7ef8146102db576100ae565b806301cb3b20146101d057806329dcb0cf146101e757806338af3eed1461020e5780636e66f6e91461023f57806370a0823114610254575b600754610100900460ff16156100c357600080fd5b33600081815260066020526040902080543490810190915560028054820190556005546004549192600160a060020a039091169163a9059cbb91908481151561010857fe5b046040518363ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004018083600160a060020a0316600160a060020a0316815260200182815260200192505050600060405180830381600087803b15801561017457600080fd5b505af1158015610188573d6000803e3d6000fd5b5050604080513381526020810185905260018183015290517fe842aea7a5f1b01049d752008c53c52890b1a6daf660cf39e8eec506112bbdf69350908190036060019150a150005b3480156101dc57600080fd5b506101e56102f0565b005b3480156101f357600080fd5b506101fc61036f565b60408051918252519081900360200190f35b34801561021a57600080fd5b50610223610375565b60408051600160a060020a039092168252519081900360200190f35b34801561024b57600080fd5b50610223610384565b34801561026057600080fd5b506101fc6004803603602081101561027757600080fd5b5035600160a060020a0316610393565b34801561029357600080fd5b506101fc6103a5565b3480156102a857600080fd5b506101fc6103ab565b3480156102bd57600080fd5b506101fc6103b1565b3480156102d257600080fd5b506101fc6103b7565b3480156102e757600080fd5b506101e56103bc565b600354421061036d576001546002541061035d576007805460ff1916600117905560005460025460408051600160a060020a039093168352602083019190915280517fec3f991caf7857d61663fd1bba1739e04abd4781238508cde554bb849d790c859281900390910190a15b6007805461ff0019166101001790555b565b60035481565b600054600160a060020a031681565b600554600160a060020a031681565b60066020526000908152604090205481565b60015481565b60025481565b60045481565b303190565b600354421061036d5760075460ff161515610469573360009081526006602052604081208054908290559081111561046757604051339082156108fc029083906000818181858888f193505050501561045457604080513381526020810183905260008183015290517fe842aea7a5f1b01049d752008c53c52890b1a6daf660cf39e8eec506112bbdf69181900360600190a1610467565b3360009081526006602052604090208190555b505b60075460ff1680156104855750600054600160a060020a031633145b1561036d57600254604051339180156108fc02916000818181858888f1935050505015610504576000805460025460408051600160a060020a03909316835260208301919091528181019290925290517fe842aea7a5f1b01049d752008c53c52890b1a6daf660cf39e8eec506112bbdf69181900360600190a161036d565b6007805460ff1916905556fea165627a7a72305820886ab40951858e8532bc5d465d6ae42c5ba2d41dd3bfe56ffd6923491c548d480029";

    public static final String FUNC_CHECKGOALREACHED = "checkGoalReached";

    public static final String FUNC_DEADLINE = "deadline";

    public static final String FUNC_BENEFICIARY = "beneficiary";

    public static final String FUNC_TOKENREWARD = "tokenReward";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_FUNDINGGOAL = "fundingGoal";

    public static final String FUNC_AMOUNTRAISED = "amountRaised";

    public static final String FUNC_PRICE = "price";

    public static final String FUNC_GETETHERBALANCE = "getEtherBalance";

    public static final String FUNC_SAFEWITHDRAWAL = "safeWithdrawal";

    public static final Event GOALREACHED_EVENT = new Event("GoalReached", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FUNDTRANSFER_EVENT = new Event("FundTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
    ;

    @Deprecated
    protected Crowdsale(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Crowdsale(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Crowdsale(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Crowdsale(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> checkGoalReached() {
        final Function function = new Function(
                FUNC_CHECKGOALREACHED, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> deadline() {
        final Function function = new Function(FUNC_DEADLINE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> beneficiary() {
        final Function function = new Function(FUNC_BENEFICIARY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> tokenReward() {
        final Function function = new Function(FUNC_TOKENREWARD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> fundingGoal() {
        final Function function = new Function(FUNC_FUNDINGGOAL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> amountRaised() {
        final Function function = new Function(FUNC_AMOUNTRAISED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> price() {
        final Function function = new Function(FUNC_PRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getEtherBalance() {
        final Function function = new Function(FUNC_GETETHERBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> safeWithdrawal() {
        final Function function = new Function(
                FUNC_SAFEWITHDRAWAL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<GoalReachedEventResponse> getGoalReachedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GOALREACHED_EVENT, transactionReceipt);
        ArrayList<GoalReachedEventResponse> responses = new ArrayList<GoalReachedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            GoalReachedEventResponse typedResponse = new GoalReachedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.recipient = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.totalAmountRaised = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<GoalReachedEventResponse> goalReachedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, GoalReachedEventResponse>() {
            @Override
            public GoalReachedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GOALREACHED_EVENT, log);
                GoalReachedEventResponse typedResponse = new GoalReachedEventResponse();
                typedResponse.log = log;
                typedResponse.recipient = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.totalAmountRaised = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<GoalReachedEventResponse> goalReachedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GOALREACHED_EVENT));
        return goalReachedEventFlowable(filter);
    }

    public List<FundTransferEventResponse> getFundTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FUNDTRANSFER_EVENT, transactionReceipt);
        ArrayList<FundTransferEventResponse> responses = new ArrayList<FundTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FundTransferEventResponse typedResponse = new FundTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.backer = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.isContribution = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FundTransferEventResponse> fundTransferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, FundTransferEventResponse>() {
            @Override
            public FundTransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FUNDTRANSFER_EVENT, log);
                FundTransferEventResponse typedResponse = new FundTransferEventResponse();
                typedResponse.log = log;
                typedResponse.backer = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.isContribution = (Boolean) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<FundTransferEventResponse> fundTransferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FUNDTRANSFER_EVENT));
        return fundTransferEventFlowable(filter);
    }

    @Deprecated
    public static Crowdsale load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Crowdsale(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Crowdsale load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Crowdsale(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Crowdsale load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Crowdsale(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Crowdsale load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Crowdsale(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Crowdsale> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String ifSuccessfulSendTo, BigInteger fundingGoalInEthers, BigInteger durationInMinutes, BigInteger etherCostOfEachToken, String addressOfTokenUsedAsReward) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(ifSuccessfulSendTo), 
                new org.web3j.abi.datatypes.generated.Uint256(fundingGoalInEthers), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken), 
                new org.web3j.abi.datatypes.Address(addressOfTokenUsedAsReward)));
        return deployRemoteCall(Crowdsale.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Crowdsale> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String ifSuccessfulSendTo, BigInteger fundingGoalInEthers, BigInteger durationInMinutes, BigInteger etherCostOfEachToken, String addressOfTokenUsedAsReward) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(ifSuccessfulSendTo), 
                new org.web3j.abi.datatypes.generated.Uint256(fundingGoalInEthers), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken), 
                new org.web3j.abi.datatypes.Address(addressOfTokenUsedAsReward)));
        return deployRemoteCall(Crowdsale.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Crowdsale> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String ifSuccessfulSendTo, BigInteger fundingGoalInEthers, BigInteger durationInMinutes, BigInteger etherCostOfEachToken, String addressOfTokenUsedAsReward) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(ifSuccessfulSendTo), 
                new org.web3j.abi.datatypes.generated.Uint256(fundingGoalInEthers), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken), 
                new org.web3j.abi.datatypes.Address(addressOfTokenUsedAsReward)));
        return deployRemoteCall(Crowdsale.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Crowdsale> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String ifSuccessfulSendTo, BigInteger fundingGoalInEthers, BigInteger durationInMinutes, BigInteger etherCostOfEachToken, String addressOfTokenUsedAsReward) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(ifSuccessfulSendTo), 
                new org.web3j.abi.datatypes.generated.Uint256(fundingGoalInEthers), 
                new org.web3j.abi.datatypes.generated.Uint256(durationInMinutes), 
                new org.web3j.abi.datatypes.generated.Uint256(etherCostOfEachToken), 
                new org.web3j.abi.datatypes.Address(addressOfTokenUsedAsReward)));
        return deployRemoteCall(Crowdsale.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class GoalReachedEventResponse {
        public Log log;

        public String recipient;

        public BigInteger totalAmountRaised;
    }

    public static class FundTransferEventResponse {
        public Log log;

        public String backer;

        public BigInteger amount;

        public Boolean isContribution;
    }
}
