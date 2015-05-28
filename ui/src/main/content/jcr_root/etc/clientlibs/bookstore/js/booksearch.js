var booksSearch = {
    init: function() {

        var that = this;

        $("#bookSearch").submit(function(event) {
              /* stop form from submitting normally */
              event.preventDefault();

              console.log("...bookSearch submitted");
              that.search(this);
        });
    },

    search: function (form) {
    	var that = this;

    	//Empty the results list
    	$('.book-results-list').empty();

		var values = $(this).serialize();
        console.log(values);

		$.ajax({
		      url: "/bin/books/search",
		      type: "get",
		      data: values,
		      success: function(data, textStatus, jqXHR){
		          that.processBookData(data);
		      },
		      error:function(){
		          alert("failure");
		      }
		    });

	},

	processBookData: function (data) {
        var that = this;

        $.each(data, function (index, book) {
        	var template = $('#book-template').clone(true);

        	template.find('[data-selector="bookTitle"]').text(book.bookTitle);
        	template.find('[data-selector="bookAuthor"]').text(book.bookAuthors[0].authorName);
        	console.log(book);
        	template.find('.results-book_url').attr('href',book.href);
            template.appendTo('.book-results-list');
        });
    }

}