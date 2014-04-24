package controllers;

import play.mvc.*;
import play.data.*;

import models.*;


  public class Application extends Controller {
  static Form<Movie> movieForm = Form.form(Movie.class);
  static Form<TicketOption> ticketOptionForm = Form.form(TicketOption.class);

  
  public static Result index() {
    return redirect(routes.Application.movies());
  }
  
  public static Result movies() {
    return ok(
      views.html.index.render(Movie.all(), movieForm)
    );
  }
  
  public static Result newMovie() {
    Form<Movie> filledForm = movieForm.bindFromRequest();
      if(filledForm.hasErrors()) {
        return badRequest(
          views.html.index.render(Movie.all(), filledForm)
        );
      } else {
        Movie.create(filledForm.get());
        return redirect(routes.Application.movies());
      }
  }
  
  public static Result deleteMovie(String id) {
    Movie.delete(id);
    return redirect(routes.Application.movies());
  }

    public static Result ticketOptions() {
        return ok(
                views.html.index.render(TicketOption.all(), ticketOptionForm)
        );
    }

    public static Result newTicketOption() {
        Form<TicketOption> filledForm = ticketOptionForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(TicketOption.all(), filledForm)
            );
        } else {
            TicketOption.create(filledForm.get());
            return redirect(routes.Application.ticketOptions());
        }
    }

    public static Result deleteTicketOption(String id) {
        TicketOption.delete(id);
        return redirect(routes.Application.ticketOptions());
    }
  
}
