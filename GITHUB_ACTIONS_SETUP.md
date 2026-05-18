# GitHub Actions Setup for Cucumber TestNG Maven Project

This document explains how to use GitHub Actions for your Cucumber TestNG Maven automation project.

## Overview

GitHub Actions automatically runs your Cucumber tests whenever you push code or create a pull request. This provides continuous integration and testing.

## Workflow Files Created

### 1. **maven-tests.yml** (Basic Workflow)
- **Location:** `.github/workflows/maven-tests.yml`
- **Triggers:** 
  - On push to `main` and `develop` branches
  - On pull requests to `main` and `develop` branches
  - Daily schedule at 9 AM UTC
- **Features:**
  - Builds the project
  - Runs Cucumber tests via Maven
  - Uploads test reports as artifacts
  - Publishes TestNG reports
  - Comments on PRs with test results

### 2. **cucumber-tests-chrome.yml** (Advanced Workflow with Browser)
- **Location:** `.github/workflows/cucumber-tests-chrome.yml`
- **Features:**
  - Installs Chrome browser
  - Runs tests in parallel (4 threads)
  - Generates and uploads detailed reports
  - Better for Selenium UI tests

## Quick Start

### Step 1: Push to GitHub
```bash
git add .github/
git commit -m "Add GitHub Actions workflows"
git push origin main
```

### Step 2: Check Workflow Status
Go to your GitHub repository → **Actions** tab to see:
- ✅ Active workflows
- ✅ Test results
- ✅ Build logs

### Step 3: View Reports
- Navigate to **Actions** → Your workflow run
- Click **Artifacts** to download:
  - `cucumber-test-reports` (HTML and JSON reports)
  - Test logs

## Customization

### Change Test Execution Threads
Edit `.github/workflows/maven-tests.yml`:
```yaml
- name: Run Cucumber Tests
  run: mvn test -Dsurefire.suiteXmlFiles=testng.xml -Dthread-count=6
```
Change `6` to your desired thread count.

### Change Trigger Schedule
Edit the `on:` section:
```yaml
schedule:
  - cron: '0 9 * * MON-FRI'  # Run Monday-Friday at 9 AM
```

### Add Additional Branches
```yaml
on:
  push:
    branches: [ main, develop, staging ]
  pull_request:
    branches: [ main, develop, staging ]
```

### Use Different Java Version
```yaml
strategy:
  matrix:
    java-version: ['17', '21']
```
This runs tests on multiple Java versions.

## Environment Variables

To add environment variables (e.g., for base URL, credentials):

```yaml
env:
  BASE_URL: https://example.com
  
- name: Run Tests
  run: mvn test -Dbase.url=${{ env.BASE_URL }}
```

## Secrets Management

For sensitive data (passwords, API keys):

1. Go to GitHub repo → **Settings** → **Secrets and variables** → **Actions**
2. Click **New repository secret**
3. Add your secret (e.g., `BROWSER_PASSWORD`)
4. Use in workflow:
```yaml
- name: Run Tests
  run: mvn test
  env:
    PASSWORD: ${{ secrets.BROWSER_PASSWORD }}
```

## Failure Notifications

GitHub Actions can notify you on:
- **Email:** Check GitHub Settings → Notifications
- **Slack:** Add a Slack integration in your repo
- **PR Comments:** Workflow already comments on PRs with results

## Viewing Test Results

### Option 1: In GitHub UI
- Go to **Actions** → Select your workflow run
- See test results and logs

### Option 2: Download Artifacts
- In workflow run → **Artifacts** section
- Download `cucumber-test-reports`
- Open `cucumber-reports.html` in browser

### Option 3: View TestNG Reports
- Click **Summary** in the workflow run
- See "Cucumber Test Results" with pass/fail counts

## Troubleshooting

### Tests Failing in CI but Passing Locally?

Common causes:
1. **Browser differences:** CI uses headless Chrome
   - Ensure your code handles headless mode
   - Check `DriverFactory.java` for headless configuration

2. **Timing issues:** CI might be slower
   - Increase wait times in `CommonUtils.java`
   - Use explicit waits instead of implicit waits

3. **Environment differences:**
   - Set base URL via environment variable
   - Ensure test data is accessible

### Solution Example
Update `DriverFactory.java`:
```java
ChromeOptions options = new ChromeOptions();
if (System.getenv("CI") != null) {
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
}
```

## Advanced Features

### Parallel Test Execution
Already configured with `data-provider-thread-count="4"` in `testng.xml`

### Test Reporting Dashboard
Install the **Test Reporter** action for better visualizations:
Already included in the workflow (dorny/test-reporter)

### Notifications
Add to workflow for Slack notifications:
```yaml
- name: Slack Notification
  if: always()
  uses: slackapi/slack-github-action@v1
  with:
    payload: |
      {
        "text": "Cucumber Tests: ${{ job.status }}"
      }
  env:
    SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK }}
```

## Performance Tips

1. **Cache Maven Dependencies:**
   - Already enabled with `cache: maven`

2. **Skip Build if Tests Only:**
   ```yaml
   - name: Run Tests Only
     run: mvn test -DskipTests=false -DskipBuild=true
   ```

3. **Run Specific Tests:**
   ```yaml
   - name: Run Specific Features
     run: mvn test -Dcucumber.filter.tags="@smoke"
   ```

## File Structure
```
your-repo/
├── .github/
│   └── workflows/
│       ├── maven-tests.yml
│       └── cucumber-tests-chrome.yml
├── src/
├── pom.xml
└── testng.xml
```

## Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| Tests timeout in CI | Increase `Duration.ofSeconds()` in CommonUtils |
| Chrome not found | Already handled by installing chromium-browser |
| Tests pass locally but fail in CI | Check environment variables and base URLs |
| Artifacts not generated | Ensure test reports path is correct in pom.xml |

## Next Steps

1. ✅ Push the workflow files to GitHub
2. ✅ Go to **Actions** tab to verify workflows run
3. ✅ Check test results and reports
4. ✅ Customize triggers and schedules as needed
5. ✅ Add secrets if needed for credentials

## Support

For more information:
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)
- [Cucumber TestNG Integration](https://cucumber.io/docs/cucumber/integrations/java-testng/)
