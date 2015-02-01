require 'sinatra'

get '/' do
  sleep 5
  headers "Content-Type" => "text/plain"
  'hello from server'
end

