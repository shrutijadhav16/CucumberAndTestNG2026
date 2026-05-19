# GitHub Actions Workflow - Simple Explanation

## What is a Branch?
Think of a branch like a **separate copy of your code**:

```
Your Repository:
├── main branch (like the "final version")
├── develop branch (like the "testing version")
└── feature branch (like your "working draft")
```

---

## Your Branches in This Project

### 1. **main branch**
- The **official/production** code
- Most important - should be stable and working

### 2. **develop branch**
- The **development/testing** version
- Where developers merge their work before it goes to main

### 3. **Other branches** (feature, bugfix, etc.)
- Temporary branches for new features or bug fixes
- Deleted after merged to develop or main

---

## What Does the Workflow Do?

### **When does it trigger (run)?**

```yaml
on:
  push:
    branches: [ main, develop ]
```
**Meaning:** When you PUSH code to `main` OR `develop` branches → RUN TESTS

```yaml
  pull_request:
    branches: [ main, develop ]
```
**Meaning:** When you create a PULL REQUEST to `main` OR `develop` branches → RUN TESTS

```yaml
  schedule:
    - cron: '0 9 * * *'
```
**Meaning:** Every day at 9 AM UTC → RUN TESTS (even if no code was pushed!)

---

## What Happens When Workflow Runs?

### **Step-by-Step Execution:**

| Step | What Happens | Purpose |
|------|-------------|---------|
| 1 | Download your code | Get the latest code from GitHub |
| 2 | Install Java 17 | Setup the environment |
| 3 | Build project | Compile your code (mvn clean install) |
| 4 | Run Tests | Execute Cucumber + TestNG tests |
| 5 | Generate Reports | Create test result reports |
| 6 | Upload Reports | Save reports as artifacts (downloadable) |
| 7 | Publish Results | Show test results in GitHub |
| 8 | Comment on PR | Add a message to your Pull Request |

---

## Real-World Example

### **Scenario 1: You push code to develop branch**

```
You write code locally
    ↓
git push origin develop
    ↓
GitHub receives the push
    ↓
Workflow automatically triggers
    ↓
Tests run automatically
    ↓
You get a report in GitHub
```

### **Scenario 2: You create a Pull Request**

```
You create a PR from feature branch → develop branch
    ↓
GitHub detects the PR
    ↓
Workflow automatically runs
    ↓
Tests execute
    ↓
Results show in the PR page (✅ passed or ❌ failed)
```

### **Scenario 3: Daily scheduled run**

```
Every day at 9 AM UTC
    ↓
Workflow automatically runs (even if no code changes)
    ↓
Tests execute on the current code
    ↓
You know if something broke
```

---

## Key Points to Remember

✅ **"main, develop"** = branch names (not placeholders)
✅ **Branches are separate copies** of your code
✅ **Workflow runs automatically** when you push/PR to these branches
✅ **All 8 steps happen without manual work**
✅ **Reports are generated automatically**
✅ **GitHub comments on PRs with results**

---

## Visual Workflow Flow

```
┌─────────────────────────────────────────────┐
│  You Push Code to main or develop branch    │
└─────────────────┬───────────────────────────┘
                  │
                  ↓
┌─────────────────────────────────────────────┐
│  GitHub Actions Workflow Starts             │
└─────────────────┬───────────────────────────┘
                  │
    ┌─────────────┼─────────────┐
    ↓             ↓             ↓
  Build      Run Tests    Generate Reports
    │             │             │
    └─────────────┼─────────────┘
                  │
                  ↓
        ┌─────────────────────┐
        │ Upload Artifacts    │
        │ Comment on PR       │
        │ Publish Results     │
        └─────────────────────┘
                  │
                  ↓
        ┌─────────────────────┐
        │ Workflow Complete   │
        │ Results in GitHub   │
        └─────────────────────┘
```

---

## Commands in Workflow Explained

```yaml
mvn clean install -DskipTests
```
- **mvn clean** = Delete old build files
- **mvn install** = Build the project
- **-DskipTests** = Don't run tests during build (save time)

```yaml
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```
- **mvn test** = Run all tests
- **-Dsurefire.suiteXmlFiles=testng.xml** = Use your testng.xml file to know which tests to run

---

## Question & Answer

**Q: What is "main branch"?**
A: A branch literally named "main" - the main/production code

**Q: What is "develop branch"?**
A: A branch literally named "develop" - the development/testing code

**Q: Do I need to create these branches manually?**
A: Usually they already exist. You just push code to them.

**Q: What if I have different branch names?**
A: Update the workflow to match your branch names:
```yaml
branches: [ master, staging ]  # if your branches are "master" and "staging"
```
