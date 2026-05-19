# Why Use matrix.java-version Instead of Environment Variables?

## Quick Answer
**matrix** allows you to **run the same workflow multiple times with different values**.
**env** is just a simple variable - it runs only once.

---

## Comparison

### ❌ **Using Environment Variables (Limited)**

```yaml
env:
  JAVA_VERSION: '17'

steps:
  - name: Set up JDK
    uses: actions/setup-java@v4
    with:
      java-version: ${{ env.JAVA_VERSION }}
```

**Problem:** Runs only ONCE with Java 17

---

### ✅ **Using Matrix (Powerful)**

```yaml
strategy:
  matrix:
    java-version: ['17']

steps:
  - name: Set up JDK ${{ matrix.java-version }}
    uses: actions/setup-java@v4
    with:
      java-version: ${{ matrix.java-version }}
```

**Benefit:** Can test MULTIPLE Java versions

---

## Real Example: Testing Multiple Java Versions

### **Matrix with Multiple Values**

```yaml
strategy:
  matrix:
    java-version: ['17', '21', '22']
```

**Result:** Workflow runs 3 times automatically:
- ✅ Run 1: Java 17
- ✅ Run 2: Java 21
- ✅ Run 3: Java 22

All tests run on all versions!

---

## Visual Comparison

### **Option 1: Environment Variable (Single Run)**

```
┌─────────────────────────────┐
│ Push to Repository          │
└────────────┬────────────────┘
             │
             ↓
┌─────────────────────────────┐
│ Workflow Starts (1 Run)     │
│ JAVA_VERSION = 17           │
└────────────┬────────────────┘
             │
         ┌───┴──────┐
         │           │
    Build       Test
         │           │
         └───┬───────┘
             │
         Reports (1 Report)
```

---

### **Option 2: Matrix (Multiple Runs)**

```
┌─────────────────────────────┐
│ Push to Repository          │
└────────────┬────────────────┘
             │
             ↓
    ┌────────┴────────┐
    │                 │
   Run 1             Run 2           Run 3
(Java 17)         (Java 21)       (Java 22)
    │                 │              │
    ↓                 ↓              ↓
  Build            Build           Build
  Test             Test            Test
  Report           Report          Report
    │                 │              │
    └────────┬────────┴──────────────┘
             │
    Reports (3 Reports)
```

---

## Your Workflow Example

```yaml
strategy:
  matrix:
    java-version: ['17']
```

Currently has only **1 Java version**, so it runs once.

---

## How to Add More Java Versions

### **Current (1 Java version):**

```yaml
strategy:
  matrix:
    java-version: ['17']
```

**Result:** 1 workflow run

---

### **Extended (3 Java versions):**

```yaml
strategy:
  matrix:
    java-version: ['17', '21', '22']
```

**Result:** 3 workflow runs (one for each version)

---

### **Even More Complex:**

```yaml
strategy:
  matrix:
    java-version: ['17', '21', '22']
    os: ['ubuntu-latest', 'windows-latest']
```

**Result:** 6 workflow runs (3 versions × 2 OS combinations)

---

## Why Use Matrix?

✅ **Test on multiple versions automatically**
✅ **Don't repeat workflow definition**
✅ **Run in parallel (faster results)**
✅ **Professional & scalable approach**

---

## When to Use Each

### **Use Matrix When:**
- You want to test multiple Java versions
- You want to test on different OS (Windows, Linux, Mac)
- You want to test different configurations
- You need parallel testing

### **Use env Variables When:**
- You need a simple constant value
- You don't need multiple runs
- You're configuring paths, URLs, etc.

---

## Your Current Workflow

Your workflow uses:
```yaml
strategy:
  matrix:
    java-version: ['17']
```

This is **good practice** because:
1. ✅ You can easily add more Java versions later
2. ✅ Follows GitHub Actions best practices
3. ✅ Ready to scale without code changes

To add Java 21 support:
```yaml
strategy:
  matrix:
    java-version: ['17', '21']
```

Done! Workflow will run on both versions automatically! 🎉

---

## Summary

| Feature | Matrix | Environment Variable |
|---------|--------|----------------------|
| Multiple values | ✅ Yes | ❌ No |
| Parallel runs | ✅ Yes | ❌ No |
| Configurations | ✅ Yes | ❌ No |
| Simple values | ⚠️ Possible | ✅ Yes |
