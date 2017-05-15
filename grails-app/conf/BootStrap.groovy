import com.synacy.mystudentgrader.student.StudentMarshaller
import com.synacy.mystudentgrader.student.grade.GradeMarshaller

class BootStrap {

    def init = { servletContext ->
        initializeMarshallers()
    }

    def destroy = {
    }

    private void initializeMarshallers() {
        new StudentMarshaller().register()
        new GradeMarshaller().register()
    }
}
