// Class to represent a row in the seat reservations grid
function Source(key, title, revision, hash, source, language, tags, created_at) {
    var self = this;
	self.key = key;
	self.title = title;
	self.revision = revision;
	self.hash = hash;
	self.source = source;
	self.language = language;
	self.tags = tags;
	self.created_at = created_at;
	self.hasLink = ko.computed(function(){
		return self.key != '' && self.revision > 0;
	});
	self.link = ko.computed(function (){
		return baseUrl +'/#' + self.key+ '/'+self.revision;
	});
}

// Overall viewmodel for this screen, along with initial state
function SourceShareViewModel() {
    var self = this;
    self.activeSource = ko.observable(new Source('','',0,'','','',[],''));
    self.baseUrl = ko.observable(baseUrl);
    
    self.sammy = Sammy(function() {
    	this.get('',function(){});
    	this.get('#:key/:revision',function(){
    		$.ajax(self.baseUrl()+'/source/'+this.params.key+'/'+this.params.revision,{
    			type: 'GET',
    			dataType: 'json'
    		}).done(function(data,textStatus,jqXHR){
    			self.activeSource(new Source(data.key,data.title,data.revision,data.hash,data.source,data.language,data.tags,data.created_at));
    		}).fail(function(jqXHR){
    			console.log('fail',jqXHR);
    		});
    	});
    });
    self.sammy.run();
    
    self.save = function() {
    	var postData = ko.toJSON(self.activeSource,['title','source','tags','language']);
    	$.ajax(self.baseUrl()+'/source',{
    		type: 'POST',
    		data : postData,
    		contentType :'application/json',
    		dataType : 'text'
    	}).done(function(data,textStatus,jqXHR){
    		var key = jqXHR.getResponseHeader('X-Source-Key');
    		var revision = jqXHR.getResponseHeader('X-Source-Revision');
    		document.location.hash = '#'+key+'/'+revision;
    	}).fail(function(jqXHR,textStatus,errorThrown){
    		console.log('Fail',jqXHR,textStatus,errorThrown);
    	});
    };
}
var viewModel =  new SourceShareViewModel();
ko.applyBindings(viewModel);