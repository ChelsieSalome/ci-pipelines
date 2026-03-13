# ci-pipelines

This repository showcases the **CI/CD pipelines** I implemented using **Jenkins** and **GitLab CI** for Python projects.  
I built these pipelines to automate testing, code reuse, and continuous integration, while keeping things modular and maintainable.

---

## Repository Structure

```
CI-Pipelines/
├─ GitLab CI/
│  ├─ ci/
│  │  └─ Jenkinsfile
│  ├─ .gitlab-ci.yml
│  ├─ base.py
│  ├─ create_tables.py
│  ├─ drop_tables.py
│  ├─ point_manager.py
│  ├─ point.py
│  ├─ points_api.py
│  ├─ requirements.txt
│  ├─ test_point_manager.py
│  └─ test_points_api.py
├─ Jenkins/
│  ├─ carLot/
│  └─ ci_functions/
│      └─ vars/
│          └─ python_build.groovy
├─ screenshots/
└─ README.md
```


# CI Pipelines Demo

This repository showcases the **CI/CD pipelines** I implemented using **Jenkins** and **GitLab CI** for Python projects. I built these pipelines to automate testing, code reuse, and continuous integration.

## Jenkins Pipeline

### What I Did
I created a Jenkins pipeline to automate testing of my Python modules.

* **Shared Library**: I created a shared library at `Jenkins/ci_functions/vars/python_build.groovy` to store reusable functions. This allowed me to follow the DRY principle.
* **Reference**: I referenced the shared library in my `Jenkinsfile` located at `GitLab CI/ci/Jenkinsfile`.
* **Stages**:
    * **Checkout**: Pull the code from the repo.
    * **Build**: Install dependencies and prepare the environment.
    * **Test**: Run my Python unit tests.
    * **Deploy/Notifications**: Optional notifications for successful builds.
* **Automation**: I configured triggers and a webhook for automatic builds.

### File References
| File | Purpose |
| :--- | :--- |
| `Jenkins/ci_functions/vars/python_build.groovy` | Shared library with reusable pipeline functions |
| `GitLab CI/ci/Jenkinsfile` | Main Jenkins pipeline definition |

### Screenshots
* Jenkins pipeline execution: `screenshots/jenkins_pipeline.png`
* Stage visualization: `screenshots/jenkins_stages.png`

---

## GitLab CI Pipeline

### What I Did
For GitLab CI, I focused on automating testing and code management for the point module.

* **Configuration**: I created the pipeline file `.gitlab-ci.yml` at `GitLab CI/.gitlab-ci.yml`.
* **Stages**:
    * **Install Dependencies**: Install Python packages from `requirements.txt`.
    * **Run Tests**: Execute unit tests (`test_point_manager.py` and `test_points_api.py`).
    * **Lint/Code Checks**: Code style verification.
* **Integration**: Integrated smoothly with my Python project structure inside `GitLab CI/`.

### File References
| File | Purpose |
| :--- | :--- |
| `GitLab CI/.gitlab-ci.yml` | Main GitLab CI pipeline definition |
| `GitLab CI/point_manager.py` | Module managing point logic |
| `GitLab CI/point.py` | Core point module |
| `GitLab CI/points_api.py` | API endpoints for points |
| `GitLab CI/create_tables.py` & `drop_tables.py` | Database setup/cleanup |
| `GitLab CI/test_point_manager.py` & `test_points_api.py` | Unit tests |
| `GitLab CI/requirements.txt` | Python dependencies |

### Screenshots
* GitLab CI pipeline: `screenshots/gitlab_ci_pipeline.png`
* Stage execution: `screenshots/gitlab_ci_stages.png`

---

## Python Project Details

| File | Purpose |
| :--- | :--- |
| `GitLab CI/base.py` | Base classes and utilities |
| `GitLab CI/point.py` | Core functionality of points |
| `GitLab CI/point_manager.py` | Manages CRUD operations |
| `GitLab CI/points_api.py` | API endpoints |
| `GitLab CI/create_tables.py` | Creates database tables |
| `GitLab CI/drop_tables.py` | Drops tables |
| `GitLab CI/test_point_manager.py` | Unit tests for point manager |
| `GitLab CI/test_points_api.py` | Unit tests for API |

* Python module organization: `screenshots/python_modules.png`

---

