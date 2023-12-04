### Use visual studio code to open this project folder.

### venv

#### It’s considered a best practice to work within a virtual environment when developing Python projects. A virtual environment allows you to isolate your project’s dependencies from the system Python installation, making it easier to manage and avoid conflicts between different projects.

#### To create a Python virtual environment, you can use the built-in venv module. Here are the steps to create a virtual environment:

Ctrl+[~]: to open terminal:

1. Open your command prompt or terminal.
2. Navigate to the directory where you want to create the virtual environment.
3. Type python (or python3) -m venv mypythonvenv and press Enter. This will create a new directory called mypythonvenv in your current directory.
4. To activate the virtual environment, type source mypythonvenv/bin/activate on macOS or Linux, or mypythonvenv\Scripts\activate.bat on Windows.
5. Once activated, you can install packages and run Python scripts within the virtual environment.
6. deactivate to exit

1、打开 Pycharm 的项目，找到下方 Terminal，打开
2、利用 cd 进入项目的 Scripts 文件夹中
3、输入 activate
4、利用 pip 命令 安装所需要的包即可
或者直接找到虚拟环境所在文件夹，cmd 即可 pip 命令安装。

###

### requirements.txt: This helps you to set up your development environment.

pip install -r requirements.txt
pip list
pip install dash
pip freeze > requirements.txt

> ==：等于
> ：大于版本
> =：大于等于
> < ：小于版本
> <=：小于等于版本
> ~=：兼容版本，使用任何大于或等于指定版本，但不大于当前发行系列的版本。

### setup.py: This helps you to create packages that you can redistribute. For your end user.

When a python user does pip install ./pkgdir_my_module (or pip install my-module), pip will run setup.py in the given directory (or module). Similarly, any module which has a setup.py can be pip-installed, e.g. by running pip install . from the same folder.

###

Select python file "main.py", and choose the venv "Python interpreter" (on the bottom of the Terminal window)
Right click "main.py" file and choose "run python file in terminal"
and visit: http://127.0.0.1:8050/
