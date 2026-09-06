# Bank Account Simulator

Console-based Java application simulating savings and current accounts: deposit, withdraw, and transfer, each account type enforcing its own withdrawal rule.

## Concepts demonstrated
- **Abstract classes** — `Account` is abstract with an abstract `withdraw()` and `describeAccountType()`, forcing every subclass to define its own rule
- **Inheritance & polymorphism** — `SavingsAccount` enforces a minimum balance; `CurrentAccount` allows a negative balance up to an overdraft limit; same `withdraw()` call, different behavior per account type
- **Custom exceptions** — `InsufficientFundsException` for unknown accounts, minimum-balance violations, and overdraft-limit violations
- **Collections** — `HashMap<String, Account>` keyed by account number for O(1) lookup
- **Transactional-style transfer** — `Bank.transfer()` only deposits into the destination after the source withdrawal succeeds, so a failed withdrawal never creates money out of nowhere
- **Template method pattern** — `Account.withdraw()` is `final` and always records a `Transaction`; it delegates only the account-specific rule check to the abstract `applyWithdrawalRule()` hook implemented by each subclass
- **Transaction history** — every deposit/withdrawal/transfer leg is recorded as an immutable `Transaction` (type, amount, balance after, description, timestamp) in a per-account list, viewable from the menu
- **Interest calculation** — `SavingsAccount` carries an annual interest rate (default 4%); `Bank.applyMonthlyInterestToSavings()` credits monthly interest (`balance * rate/12/100`) to every savings account via `instanceof` pattern matching, leaving current accounts untouched

## Run it
```bash
cd src
javac *.java
java BankAccountSimulator
```
