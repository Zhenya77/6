package commands;


import controller.ClientCommand;
import controller.CommandWithoutArg;

public class Help implements CommandWithoutArg, ClientCommand {
    String name = "help";

    /**
     * show list of all commands
     *
     * @param o
     */
    @Override
    public String execute(Object o) {
        return ("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert null {element} : добавить новый элемент с заданным ключом\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_key null : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "history : вывести последние 5 команд (без их аргументов)\n" +
                "replace_if_greater null {element} : заменить значение по ключу, если новое значение больше старого\n" +
                "remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +
                "remove_all_by_description description : удалить из коллекции все элементы, значение поля description которого эквивалентно заданному\n" +
                "average_of_age : вывести среднее значение поля age для всех элементов коллекции\n" +
                "min_by_description : вывести любой объект из коллекции, значение поля description которого является минимальным");
    }

    @Override
    public String getName() {
        return name;
    }
}
